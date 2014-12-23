package com.searshc.mygarage.entities;

import java.io.Serializable;

public class QBankQuestionChoice implements Serializable {
	private static final long serialVersionUID = 5010485227088209625L;
	
	private String answerChoiceID;
	private String answerTxt;
	private Boolean hasFollowup;
	
	private String followupQuestionTextID;
	private String answerID;	
	private String followupAnswerID;

    public QBankQuestionChoice() {
    }

    public QBankQuestionChoice(String answerChoiceID, String answerTxt) {
        super();
        this.setAnswerChoiceID(answerChoiceID);
        this.setAnswerTxt(answerTxt);
    }

	public String getAnswerChoiceID() {
		return answerChoiceID;
	}

	public void setAnswerChoiceID(String answerChoiceID) {
		this.answerChoiceID = answerChoiceID;
	}

	public String getAnswerTxt() {
		return answerTxt;
	}

	public void setAnswerTxt(String answerTxt) {
		this.answerTxt = answerTxt;
	}

	public Boolean getHasFollowup() {
		return hasFollowup;
	}

	public void setHasFollowup(Boolean hasFollowup) {
		this.hasFollowup = hasFollowup;
	}

	public String getFollowupAnswerID() {
		return followupAnswerID;
	}

	public void setFollowupAnswerID(String followupAnswerID) {
		this.followupAnswerID = followupAnswerID;
	}

	public String getAnswerID() {
		return answerID;
	}

	public void setAnswerID(String answerID) {
		this.answerID = answerID;
	}

	public String getFollowupQuestionTextID() {
		return followupQuestionTextID;
	}

	public void setFollowupQuestionTextID(String followupQuestionTextID) {
		this.followupQuestionTextID = followupQuestionTextID;
	}
}