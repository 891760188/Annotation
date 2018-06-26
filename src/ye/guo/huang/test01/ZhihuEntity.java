package ye.guo.huang.test01;

import java.util.ArrayList;
import java.util.List;

public class ZhihuEntity {
	 private String question;// 问题
	 private String questionDescription;// 问题描述
	 private String zhihuUrl;// 网页链接
	 private List<String> answers;// 存储所有回答的数组
	 // 构造方法初始化数据
	 public ZhihuEntity() {
	  question = "";
	  zhihuUrl = "";
	  answers = new ArrayList<String>();
	 }
	 public ZhihuEntity(String question,String questionDescription,String zhihuUrl,List<String> answers) {
		this.question = question ;
		this.zhihuUrl = zhihuUrl ;
		answers = answers;
	 }
	 
	 
	 
	 
	 public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getZhihuUrl() {
		return zhihuUrl;
	}
	public void setZhihuUrl(String zhihuUrl) {
		this.zhihuUrl = zhihuUrl;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	@Override
	 public String toString() {
	  return "问题：" + question +"\n问题描述：" + questionDescription + "\n链接：" + zhihuUrl + "\n回答：" + answers + "\n";
	 }
}
