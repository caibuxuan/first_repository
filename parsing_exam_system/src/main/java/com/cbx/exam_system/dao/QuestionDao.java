package com.cbx.exam_system.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cbx.exam_system.entity.Question;
import com.cbx.exam_system.util.MybatisUtil;

public class QuestionDao {
	/**
	 * 添加一条数据
	 * @param question
	 */
	public void add(Question question){
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.insert("questionNameSpace.insertQuestion", question);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			MybatisUtil.closeSqlSession();
		}
	}
	
	@Test
	public void addTest(){
		QuestionDao questionDao = new QuestionDao();
		questionDao.add(new Question(1,1,"哈哈","11","22","33","44","55",1));
	}

}
