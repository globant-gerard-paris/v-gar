package com.searshc.mygarage.apis.qbank.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "POSPrompt")
@XmlAccessorType(XmlAccessType.FIELD)
public class POSPromptQuestionModel {
	@XmlElement(name="PromptGroupName")
    private String promptGroupName;
	
	@XmlElement(name="QuestionPackageID")
	private String questionPackageID;
	
	@XmlElement(name="AttributeID")
	private String attributeID;
    
	@XmlElement(name="QuestionRuleID")
	private String questionRuleID;
	
	// TODO: Need to add ansertext?
    
	@XmlElement(name="QuestionTextID")
	private String questionTextID;
	
	@XmlElement(name = "AnswerOption")
	@XmlElementWrapper(name = "AnswerChoices")
	private List<AnswerChoiceModel> answerChoices = new ArrayList<AnswerChoiceModel>();

	@XmlElement(name = "FollowupQuestion")
	@XmlElementWrapper(name = "FollowupQuestions")
	private List<FollowupQuestionResponseModel> followupQuestions = new ArrayList<FollowupQuestionResponseModel>();
	
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

	public List<AnswerChoiceModel> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<AnswerChoiceModel> answerChoices) {
		this.answerChoices = answerChoices;
	}
	
	public void addAnswerChoice(AnswerChoiceModel answerChoice) {
		if (answerChoices == null)
			answerChoices = new ArrayList<AnswerChoiceModel>();
		answerChoices.add(answerChoice);
	}
	
	public void addFollowupQuestion(FollowupQuestionResponseModel followupQuestionResponseModel) {
		if (followupQuestions == null)
			followupQuestions = new ArrayList<FollowupQuestionResponseModel>();
		followupQuestions.add(followupQuestionResponseModel);
	}
	
	public List<FollowupQuestionResponseModel> getFollowupQuestions() {
		return followupQuestions;
	}

	public void setFollowupQuestions(List<FollowupQuestionResponseModel> followupQuestions) {
		this.followupQuestions = followupQuestions;
	}
}