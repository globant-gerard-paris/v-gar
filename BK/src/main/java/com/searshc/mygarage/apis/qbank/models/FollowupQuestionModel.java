package com.searshc.mygarage.apis.qbank.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class FollowupQuestionModel {
	@XmlElement(name="FollowupQuestionTextID")
	private String followupQuestionTextID;
	
	@XmlElement(name="QuestionToFollowup")
	private String questionToFollowup; 
	
	@XmlElement(name="FollowupAttributeID")
	private String followupAttributeID;

	@XmlElement(name="FollowupQuestionLine1")
	private String followupQuestionLine1;

	@XmlElement(name="FollowupQuestionLine2")
	private String followupQuestionLine2;

	@XmlElement(name="FollowupQuestionLine3")
	private String followupQuestionLine3;

	@XmlElement(name="FollowupQuestionLine4")
	private String followupQuestionLine4;

	@XmlElement(name="FollowupQuestionLine5")
	private String followupQuestionLine5;
	
	@XmlElement(name="Answer")
	@XmlElementWrapper(name="AnswerstoFollowup")
	private List<FollowupAnswerModel> answersToFollowup;
	
	@XmlElement(name="FollowupAnswerChoice")
	@XmlElementWrapper(name="FollowupAnswerChoices")
	private List<FollowupAnswerChoiceModel> followupAnswerChoices;
	
	public List<FollowupAnswerModel> getAnswersToFollowup() {
		return answersToFollowup;
	}
	
	public void setAnswersToFollowup(List<FollowupAnswerModel> answersToFollowup) {
		this.answersToFollowup = answersToFollowup;
	}
	
	public String getFollowupQuestionTextID() {
		return followupQuestionTextID;
	}

	public void setFollowupQuestionTextID(String followupQuestionTextID) {
		this.followupQuestionTextID = followupQuestionTextID;
	}

	public String getQuestionToFollowup() {
		return questionToFollowup;
	}

	public void setQuestionToFollowup(String questionToFollowup) {
		this.questionToFollowup = questionToFollowup;
	}

	public String getFollowupAttributeID() {
		return followupAttributeID;
	}

	public void setFollowupAttributeID(String followupAttributeID) {
		this.followupAttributeID = followupAttributeID;
	}
	
	public String getFollowupQuestionLine1() {
		return followupQuestionLine1;
	}

	public void setFollowupQuestionLine1(String followupQuestionLine1) {
		this.followupQuestionLine1 = followupQuestionLine1;
	}
	
	public String getFollowupQuestionLine2() {
		return followupQuestionLine2;
	}

	public void setFollowupQuestionLine2(String followupQuestionLine2) {
		this.followupQuestionLine2 = followupQuestionLine2;
	}
	
	public String getFollowupQuestionLine3() {
		return followupQuestionLine3;
	}

	public void setFollowupQuestionLine3(String followupQuestionLine3) {
		this.followupQuestionLine3 = followupQuestionLine3;
	}
	
	public String getFollowupQuestionLine4() {
		return followupQuestionLine4;
	}

	public void setFollowupQuestionLine4(String followupQuestionLine4) {
		this.followupQuestionLine4 = followupQuestionLine4;
	}
	
	public String getFollowupQuestionLine5() {
		return followupQuestionLine5;
	}

	public void setFollowupQuestionLine5(String followupQuestionLine5) {
		this.followupQuestionLine5 = followupQuestionLine5;
	}

	public List<FollowupAnswerChoiceModel> getFollowupAnswerChoices() {
		return followupAnswerChoices;
	}

	public void setFollowupAnswerChoices(List<FollowupAnswerChoiceModel> followupAnswerChoices) {
		this.followupAnswerChoices = followupAnswerChoices;
	}
}