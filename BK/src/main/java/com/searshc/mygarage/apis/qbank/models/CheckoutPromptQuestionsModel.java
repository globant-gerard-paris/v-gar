package com.searshc.mygarage.apis.qbank.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CheckoutPromptQuestions")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckoutPromptQuestionsModel {
	@XmlElement(name="PromptGroupName")
	private String promptGroupName;
	
	@XmlElement(name="QuestionPriorityNum")
	private String questionPriorityNum; 
	
	@XmlElement(name="AttributeID")
	private String attributeID;
	
	@XmlElement(name="QuestionPackageID")
	private String questionPackageID;
	
	@XmlElement(name="QuestionRuleID")
	private String questionRuleID;
	
	@XmlElement(name="QuestionTextID")
	private String questionTextID;
	
	@XmlElement(name="QuestionTitle")
	private String questionTitle;
	
	@XmlElement(name="QuestionLine1")
	private String questionLine1;
	
	@XmlElement(name="QuestionLine2")
	private String questionLine2;
	
	@XmlElement(name="QuestionLine3")
	private String questionLine3;
	
	@XmlElement(name="QuestionLine4")
	private String questionLine4;
	
	@XmlElement(name="QuestionLine5")
	private String questionLine5;
	
	@XmlElement(name="AnswerTemplate")
	private String answerTemplate;
	
	@XmlElement(name="ApiToUse")
	private String apiToUse;
	
	@XmlElement(name="MemberAttribute")
	private String memberAttribute;
	
	@XmlElement(name="AdditionalAttrName")
	private String additionalAttrName;
	
	@XmlElement(name="AnswerOption")
	@XmlElementWrapper(name="AnswerChoices")
	private List<AnswerOptionModel> answerChoices;

	@XmlElement(name="FollowupQuestion")
	@XmlElementWrapper(name="FollowupQuestions")
	private List<FollowupQuestionModel> followupQuestions;
	
	public List<FollowupQuestionModel> getFollowupQuestions() {
		return followupQuestions;
	}
	
	public void setFollowupQuestions(List<FollowupQuestionModel> followupQuestions) {
		this.followupQuestions = followupQuestions;
	}
	
	public String getPromptGroupName() {
		return promptGroupName;
	}

	public void setPromptGroupName(String promptGroupName) {
		this.promptGroupName = promptGroupName;
	}

	public String getQuestionPriorityNum() {
		return questionPriorityNum;
	}

	public void setQuestionPriorityNum(String questionPriorityNum) {
		this.questionPriorityNum = questionPriorityNum;
	}

	public String getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}

	public String getQuestionPackageID() {
		return questionPackageID;
	}

	public void setQuestionPackageID(String questionPackageID) {
		this.questionPackageID = questionPackageID;
	}

	public String getQuestionRuleID() {
		return questionRuleID;
	}

	public void setQuestionRuleID(String questionRuleID) {
		this.questionRuleID = questionRuleID;
	}

	public String getQuestionTextID() {
		return questionTextID;
	}

	public void setQuestionTextID(String questionTextID) {
		this.questionTextID = questionTextID;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionLine2() {
		return questionLine2;
	}

	public void setQuestionLine2(String questionLine2) {
		this.questionLine2 = questionLine2;
	}

	public String getQuestionLine1() {
		return questionLine1;
	}

	public void setQuestionLine1(String questionLine1) {
		this.questionLine1 = questionLine1;
	}

	public String getQuestionLine3() {
		return questionLine3;
	}

	public void setQuestionLine3(String questionLine3) {
		this.questionLine3 = questionLine3;
	}

	public String getQuestionLine4() {
		return questionLine4;
	}

	public void setQuestionLine4(String questionLine4) {
		this.questionLine4 = questionLine4;
	}

	public String getQuestionLine5() {
		return questionLine5;
	}

	public void setQuestionLine5(String questionLine5) {
		this.questionLine5 = questionLine5;
	}

	public String getAnswerTemplate() {
		return answerTemplate;
	}

	public void setAnswerTemplate(String answerTemplate) {
		this.answerTemplate = answerTemplate;
	}

	public String getApiToUse() {
		return apiToUse;
	}

	public void setApiToUse(String apiToUse) {
		this.apiToUse = apiToUse;
	}

	public String getMemberAttribute() {
		return memberAttribute;
	}

	public void setMemberAttribute(String memberAttribute) {
		this.memberAttribute = memberAttribute;
	}

	public String getAdditionalAttrName() {
		return additionalAttrName;
	}

	public void setAdditionalAttrName(String additionalAttrName) {
		this.additionalAttrName = additionalAttrName;
	}

	public List<AnswerOptionModel> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerOptions(List<AnswerOptionModel> answerChoices) {
		this.answerChoices = answerChoices;
	}
}