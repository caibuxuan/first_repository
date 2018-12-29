package com.cbx.exam_system;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TestData {

	@Test
	public void getData(){
        //File file = new File("F:/试题html/工单考试/acrWg25962");
        File file = new File("F:/试题html/常规服务考试-航空业务和产品体系部分/yFdVQ25859");
        
		try {
			Document doc = Jsoup.parse(file, "UTF-8", "");
			//class等于item sc的div标签元素，注意selector选择器里面有空格是无效的哦，试过了；比如"item sc"这样就无效了
			Elements first = doc.select("div.group-title");
			
			System.out.println("单选题----------------------------");
			
			//单选题，所有题目的div块
			Elements select = first.select("div.sc");
			//遍历每道题的内容（题目，选项，答案...）
			for(Element element:select){
				//题目
				System.out.println("题目："+element.select("div.item-stem").text());
				
				//选项，有可能是图片，所以需要把图片的路径解析出来
				Elements options = element.select("div.opt-main");
				System.out.println("选项：");
				for(Element option:options){
					//option.text();//获得选项字母
					//option.select("img[src$=.jpg]").attr("src");//获得图片路径(以.jpg结尾的img标签元素的src属性值)
					System.out.println(""+option.text()+option.select("img[src$=.jpg]").attr("src"));
				}
				
				//得分评语
				System.out.println("得分评语："+element.select("div.get-score").text());
				//正确答案
				System.out.println("正确答案："+element.select("div.txt-rt").text());
				System.out.println();
			}
			
			System.out.println("多选题----------------------------");
			
			//多选题，所有题目
			Elements select1 = first.select("div.mc");
			//遍历每道题的内容（题目，选项，答案...）
			for(Element element:select1){
				//题目
				System.out.println("题目："+element.select("div.item-stem").text());
				
				//选项，有可能是图片，所以需要把图片的路径解析出来
				Elements options = element.select("div.opt-main");
				System.out.println("选项：");
				for(Element option:options){
					System.out.println(""+option.text()+option.select("img[src$=.jpg]").attr("src"));
				}
				//得分评语
				System.out.println("得分评语："+element.select("div.get-score").text());
				//正确答案
				System.out.println("正确答案："+element.select("div.txt-rt").text());
				System.out.println();
			}
			
			//判断题
			Elements select2 = first.select("div.tf");
			System.out.println("判断题-----------------------------------------");
			for(Element element:select2){
				//题目
				System.out.println("题目："+element.select("div.item-stem").text());
				
				//选项
				System.out.println("选项："+element.select("ul.item-opts").text());
				//得分评语
				System.out.println("得分评语："+element.select("div.get-score").text());
				//正确答案
				System.out.println("正确答案："+element.select("div.txt-rt").text());
				System.out.println();
			}
			
			//填空题
			Elements select3 = first.select("div.mq-in");
			System.out.println("填空题------------------------------------------");
			for(Element element:select3){
				//题目
				System.out.println("题目："+element.select("div.item-stem").text());
				//考生答案
				System.out.println("考生答案："+element.select("td").get(1).text());
				//参考答案
				System.out.println("参考答案："+element.select("td").get(2).text());
				//试题解析
				System.out.println("试题解析："+element.select("td").get(3).text());
				//得分评语
				System.out.println("得分评语："+element.select("td").get(4).text());
				System.out.println();
			}
			
			//简答题
			Elements select4 = first.select("div.sa");
			System.out.println("简答题-----------------------------------------");
			for(Element element:select4){
				//题目
				System.out.println("题目："+element.select("div.item-stem").text());
				//考生答案,可能是字符答案；有可能是一个附件，所以将附件的路径取出来
				//element.select("div.item-answer").select("a").attr("href");//获取附件的路径
				System.out.println(element.select("div.item-real-answer").text()+"："+element.select("div.item-real-answer").select("a").attr("href"));
				//参考答案
				System.out.println(element.select("div.item-answer").text());
				//得分评语
				System.out.println("得分评语："+element.select("div.item-get-score").text());
				System.out.println();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
