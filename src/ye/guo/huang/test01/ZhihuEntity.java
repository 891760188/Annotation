package ye.guo.huang.test01;

import java.util.ArrayList;
import java.util.List;

public class ZhihuEntity {
	 private String question;// ����
	 private String questionDescription;// ��������
	 private String zhihuUrl;// ��ҳ����
	 private List<String> answers;// �洢���лش������
	 // ���췽����ʼ������
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
	  return "���⣺" + question +"\n����������" + questionDescription + "\n���ӣ�" + zhihuUrl + "\n�ش�" + answers + "\n";
	 }
}
