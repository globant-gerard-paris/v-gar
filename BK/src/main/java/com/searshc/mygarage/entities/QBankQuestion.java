package com.searshc.mygarage.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.searshc.mygarage.apis.qbank.models.AnswerChoiceModel;

public class QBankQuestion implements Serializable {
	private static final long serialVersionUID = -2925522611479982667L;

	private String promptGroupName;
	private String questionPackageID;
	private String attributeID;
	private String questionTextID;
	private String questionText;
	private String answerTemplate;
	private String questionRuleID;
	private String followupQuestion;
	private List<QBankQuestionChoice> choices;

	public QBankQuestion() {
    }

    public QBankQuestion(String promptGroupName, String questionPackageID, String attributeID, String questionTextID, String questionText, String answerTemplate, String questionRuleID, List<QBankQuestionChoice> choices) {
        super();
        this.setPromptGroupName(promptGroupName);
        this.setQuestionPackageID(questionPackageID);
        this.setAttributeID(attributeID);
        this.setQuestionTextID(questionTextID);
        this.setQuestionText(questionText);
        this.setAnswerTemplate(answerTemplate);
        this.setQuestionRuleID(questionRuleID);
        this.setChoices(choices);
    }

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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerTemplate() {
		return answerTemplate;
	}

	public void setAnswerTemplate(String answerTemplate) {
		this.answerTemplate = answerTemplate;
	}

	public List<QBankQuestionChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<QBankQuestionChoice> choices) {
		this.choices = choices;
	}
	
	public void addChoice(QBankQuestionChoice choice) {
		if (choices == null)
			choices = new ArrayList<QBankQuestionChoice>();
		choices.add(choice);
	}

	public String getQuestionRuleID() {
		return questionRuleID;
	}

	public void setQuestionRuleID(String questionRuleID) {
		this.questionRuleID = questionRuleID;
	}

	public String getFollowupQuestion() {
		return followupQuestion;
	}

	public void setFollowupQuestion(String followupQuestion) {
		this.followupQuestion = followupQuestion;
	}
}