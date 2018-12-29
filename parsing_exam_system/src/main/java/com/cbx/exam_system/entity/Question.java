package com.cbx.exam_system.entity;


public class Question{
	
	private int qId;//题目编号id
	private int qType;//题目类型，0，1，2，3，4分别表示单选题，多选题，判断题，填空题，简答题
	private String qTitle;//题目内容
	private String qOptions;//选项内容（把ABCD...所有选项合并为一项，进行操作）
	private String qExamAnswer;//考生答案
	private String qRightAnswer;//正确（参考）答案
	private String qAnalysis;//试题分析
	private String qScore;//得分（评语）
	private int qExamType;//考试类型
	
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public int getqType() {
		return qType;
	}
	public void setqType(int qType) {
		this.qType = qType;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqOptions() {
		return qOptions;
	}
	public void setqOptions(String qOptions) {
		this.qOptions = qOptions;
	}
	public String getqExamAnswer() {
		return qExamAnswer;
	}
	public void setqExamAnswer(String qExamAnswer) {
		this.qExamAnswer = qExamAnswer;
	}
	public String getqRightAnswer() {
		return qRightAnswer;
	}
	public void setqRightAnswer(String qRightAnswer) {
		this.qRightAnswer = qRightAnswer;
	}
	public String getqAnalysis() {
		return qAnalysis;
	}
	public void setqAnalysis(String qAnalysis) {
		this.qAnalysis = qAnalysis;
	}
	public String getqScore() {
		return qScore;
	}
	public void setqScore(String qScore) {
		this.qScore = qScore;
	}
	public int getqExamType() {
		return qExamType;
	}
	public void setqExamType(int qExamType) {
		this.qExamType = qExamType;
	}
	public Question(int qId, int qType, String qTitle, String qOptions, String qExamAnswer, String qRightAnswer,
			String qAnalysis, String qScore, int qExamType) {
		super();
		this.qId = qId;
		this.qType = qType;
		this.qTitle = qTitle;
		this.qOptions = qOptions;
		this.qExamAnswer = qExamAnswer;
		this.qRightAnswer = qRightAnswer;
		this.qAnalysis = qAnalysis;
		this.qScore = qScore;
		this.qExamType = qExamType;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
