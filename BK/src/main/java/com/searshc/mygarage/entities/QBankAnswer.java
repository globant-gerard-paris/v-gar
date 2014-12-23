package com.searshc.mygarage.entities;

import java.util.List;

public class QBankAnswer {
	private String promptGroupName;
	private String questionPackageID;
	private String attributeID;
	private String questionTextID;
	private String questionRuleID;
	private String freeText;
	private String followupAnswerID; // What is this?
	private String answerTemplate;
	private String followupAttributeID;
	private String followupQuestionTextID;
	private String followupAnswerChoiceID;
	
	private List<QBankAnswerChoice> answerChoices;

	public String getPromptGroupName() {
		return promptGroupName;
	}

	public void setPromptGroupName(String promptGroupName) {
		this.promptGroupName = promptGroupName;
	}

	public String getQuestionPackageID() {
		return questionPackageID;
	}

	public void setQuestionPackageID(String questionPackageID) {
		this.questionPackageID = questionPackageID;
	}

	public String getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}

	public String getQuestionTextID() {
		return questionTextID;
	}

	public void setQuestionTextID(String questionTextID) {
		this.questionTextID = questionTextID;
	}

	public String getQuestionRuleID() {
		return questionRuleID;
	}

	public void setQuestionRuleID(String questionRuleID) {
		this.questionRuleID = questionRuleID;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public List<QBankAnswerChoice> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<QBankAnswerChoice> answerChoices) {
		this.answerChoices = answerChoices;
	}

	public String getFollowupAnswerID() {
		return followupAnswerID;
	}

	public void setFollowupAnswerID(String followupAnswerID) {
		this.followupAnswerID = followupAnswerID;
	}

	public String getAnswerTemplate() {
		return answerTemplate;
	}

	public void setAnswerTemplate(String answerTemplate) {
		this.answerTemplate = answerTemplate;
	}

	public String getFollowupAttributeID() {
		return followupAttributeID;
	}

	public void setFollowupAttributeID(String followupAttributeID) {
		this.followupAttributeID = followupAttributeID;
	}

	public String getFollowupQuestionTextID() {
		return followupQuestionTextID;
	}

	public void setFollowupQuestionTextID(String followupQuestionTextID) {
		this.followupQuestionTextID = followupQuestionTextID;
	}

	public String getFollowupAnswerChoiceID() {
		return followupAnswerChoiceID;
	}

	public void setFollowupAnswerChoiceID(String followupAnswerChoiceID) {
		this.followupAnswerChoiceID = followupAnswerChoiceID;
	}
}