package com.cbx.exam_system.control;

import org.junit.Test;

import com.cbx.exam_system.dao.QuestionDao;
import com.cbx.exam_system.entity.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 程序执行类，执行此类的getExamData()方法，即可爬去数据
 * @author pc
 *
 */
public class DataManage {
	//创建QuestionDao对象，用户处理数据
	QuestionDao questionDao = new QuestionDao();
	
	@Test
	public void getExamData(){
		long startTime = System.currentTimeMillis();
		/*
		 * 这个文件夹“F:/试题html”里面，还要区分不同考试类型，以数字标识
		 * 0：常规服务考试-航空业务和产品体系部分
		 * 1：常规服务考试-系统托管服务需求处理部分
		 * 2：工单考试
		 */
		traverseFolder("F:/试题html/常规服务考试-航空业务和产品体系部分",0);
		traverseFolder("F:/试题html/常规服务考试-系统托管服务需求处理部分",1);
		traverseFolder("F:/试题html/工单考试",2);
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行了："+(endTime-startTime)+"ms");

	}
	
	/**
	 * 遍历文件夹下的所有文件
	 * @param path 文件路径
	 * @param exam_type 文件类型
	 */
	public void traverseFolder(String path,int exam_type) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
            	//遍历每一个html文件
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath(),exam_type);
                    } else {
                    	//调用方法处理html文件，解析里面的数据
                    	parsing(file2,exam_type);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	/**
	 * 用于生成Element，并调用相关方法处理数据
	 * @param file 试题文件
	 * @param exam_type 考试类型
	 */
	public void parsing(File file,int exam_type){
		Document doc;
		try {
			//解析文件，生成Document对象
			doc = Jsoup.parse(file, "UTF-8", "");
			//class等于item sc的div标签元素。注意selector选择器里面有空格是无效的哦，试过了；比如"item sc"这样就无效了
			Elements e = doc.select("div.group-title");
			
			//创建试题实体，并调用各种试题方法解析题目数据
			Question singleQuestion = new Question();
			singleQuestion.setqExamType(exam_type);//设置考试类型
			singlePart(e, singleQuestion);//调用单选题方法
			
			Question multiQuestion = new Question();
			multiQuestion.setqExamType(exam_type);//
			multiPart(e, multiQuestion);//调用多选题方法
			
			Question judgeQuestion = new Question();
			judgeQuestion.setqExamType(exam_type);//设置考试类型
			judgePart(e, judgeQuestion);//调用判断题方法
			
			Question mq_inQuestion = new Question();
			mq_inQuestion.setqExamType(exam_type);//设置考试类型
			mq_inPart(e, mq_inQuestion);//调用填空题方法
			
			Question saQuestion = new Question();
			saQuestion.setqExamType(exam_type);//设置考试类型
			saPart(e, saQuestion);//调用简答题方法
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析单选题部分
	 * @param e
	 * @param q
	 */
	public void singlePart(Elements e,Question q){
		//单选题，所有题目的div块
		Elements select = e.select("div.sc");
		//遍历每道题的内容（题目，选项，答案...）
		for(Element element:select){
			//题型，单选题（以0表示）
			q.setqType(0); 
			
			//题目
			q.setqTitle(element.select("div.item-stem").text());
			
			/*
			 * 选项，有可能是图片，所以需要把图片的路径解析出来;所有选项拼接成一个字符串
			 * 选项排序有点乱，要处理成排序正确的选项
			 * 先用list集合存储每一个选项，然后进行排序
			 * 最后把所有选项拼接成一个字符串来存储
			 */
			List<String> optionList = new ArrayList<String>();
			StringBuilder singleOptionString = new StringBuilder();
			Elements options = element.select("div.opt-main");
			for(Element option:options){
				//option.text();//获得选项字母
				//option.select("img[src$=.jpg]").attr("src");//获得图片路径(以.jpg结尾的img标签元素的src属性值)
				optionList.add(option.text()+option.select("img[src$=.jpg]").attr("src"));
			}
			//排序
			Collections.sort(optionList);
			//遍历list，拼接成一个字符串
			for(String str:optionList){
				singleOptionString.append(str);
			}
			q.setqOptions(singleOptionString.toString());
			
			//得分评语
			q.setqScore(element.select("div.get-score").text());
			
			//正确答案
			q.setqRightAnswer(element.select("div.txt-rt").text());
			
			//调用dao层方法，添加一条数据
			questionDao.add(q);
		}
	}
	
	/**
	 * 解析多选题部分
	 * @param e
	 * @param q
	 */
	public void multiPart(Elements e,Question q){
		//多选题，所有题目
		Elements select1 = e.select("div.mc");
		//遍历每道题的内容（题目，选项，答案...）
		for(Element element:select1){
			//题型，多选题（以1表示）
			q.setqType(1);
			
			//题目
			q.setqTitle(element.select("div.item-stem").text());
			
			/*
			 * 选项，有可能是图片，所以需要把图片的路径解析出来;所有选项拼接成一个字符串
			 * 选项排序有点乱，要处理成排序正确的选项
			 * 先用list集合存储每一个选项，然后进行排序
			 * 最后把所有选项拼接成一个字符串来存储
			 */
			List<String> optionList = new ArrayList<String>();
			StringBuilder multiOptionString = new StringBuilder();
			Elements options = element.select("div.opt-main");
			for(Element option:options){
				optionList.add(option.text()+option.select("img[src$=.jpg]").attr("src"));
			}
			//排序
			Collections.sort(optionList);
			//遍历list，拼接成一个字符串
			for(String str:optionList){
				multiOptionString.append(str);
			}
			q.setqOptions(multiOptionString.toString());
			
			//得分评语
			q.setqScore(element.select("div.get-score").text());
			
			//正确答案
			q.setqRightAnswer(element.select("div.txt-rt").text());
			
			//调用dao层方法，添加一条数据
			questionDao.add(q);
		}
	}
	
	/**
	 * 解析判断题部分
	 * @param e
	 * @param q
	 */
	public void judgePart(Elements e,Question q){
		//判断题
		Elements select2 = e.select("div.tf");
		for(Element element:select2){
			//题型，判断题（以2标识）
			q.setqType(2);
			//题目
			q.setqTitle(element.select("div.item-stem").text());
			//选项
			q.setqOptions(element.select("ul.item-opts").text());
			//得分评语
			q.setqScore(element.select("div.get-score").text());
			//正确答案
			q.setqRightAnswer(element.select("div.txt-rt").text());
			
			//调用dao层方法，添加一条数据
			questionDao.add(q);
		}
	}
	
	/**
	 * 解析填空题部分
	 * @param e
	 * @param q
	 */
	public void mq_inPart(Elements e,Question q){
		//填空题
		Elements select3 = e.select("div.mq-in");
		for(Element element:select3){
			//题型，填空题（以3表示）
			q.setqType(3);
			//题目，简答题需要标明横线的，所以先判断横线的位置，然后拼接一个横线字符
			String html = element.select("div.item-stem").html();
			if(html.indexOf("<span") !=-1){
				//System.out.println("html:"+html);//输出的字符串为什么自动换行了呢？
				String pre = html.substring(0, html.indexOf("<span")).trim();//没办法，只能通过trim()方法去掉前后的无用字符
				String after = html.substring(html.lastIndexOf("span>")+5);
				String title = pre+"____"+after;
				q.setqTitle(title);
			}else{
				q.setqTitle(html);
			}
			//考生答案
			q.setqExamAnswer(element.select("td").get(1).text());
			//参考答案
			q.setqRightAnswer(element.select("td").get(2).text());
			//试题解析
			q.setqAnalysis(element.select("td").get(3).text());
			//得分评语
			q.setqScore(element.select("td").get(4).text());
			
			//调用dao层方法，添加一条数据
			questionDao.add(q);
		}
	}
	
	/**
	 * 解析简答题部分
	 * @param e
	 * @param q
	 */
	public void saPart(Elements e,Question q){
		//简答题
		Elements select4 = e.select("div.sa");
		for(Element element:select4){
			//题型，填空题（以4表示）
			q.setqType(4);
			//题目
			q.setqTitle(element.select("div.item-stem").text());
			//考生答案,可能是字符答案；有可能是一个附件，所以将附件的路径取出来
			//element.select("div.item-answer").select("a").attr("href");//获取附件的路径
			q.setqExamAnswer(element.select("div.item-real-answer").text()+"："+element.select("div.item-real-answer").select("a").attr("href"));
			//参考答案
			q.setqRightAnswer(element.select("div.item-answer").text());
			//得分评语
			q.setqScore(element.select("div.item-get-score").text());
			
			//调用dao层方法，添加一条数据
			questionDao.add(q);
		}
	}

}
