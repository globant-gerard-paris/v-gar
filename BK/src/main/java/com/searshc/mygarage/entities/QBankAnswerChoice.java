package com.searshc.mygarage.entities;

public class QBankAnswerChoice {
	private String answerChoiceID;
	private String answerTxt;
	private String followupQuestionTextID;
	private String answerID;
	private String followupAnswerID;
	private Boolean hasFollowup;

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

	public String getFollowupQuestionTextID() {
		return followupQuestionTextID;
	}

	public void setFollowupQuestionTextID(String followupQuestionTextID) {
		this.followupQuestionTextID = followupQuestionTextID;
	}

	public String getAnswerID() {
		return answerID;
	}

	public void setAnswerID(String answerID) {
		this.answerID = answerID;
	}

	public String getFollowupAnswerID() {
		return followupAnswerID;
	}

	public void setFollowupAnswerID(String followupAnswerID) {
		this.followupAnswerID = followupAnswerID;
	}

	public Boolean getHasFollowup() {
		return hasFollowup;
	}

	public void setHasFollowup(Boolean hasFollowup) {
		this.hasFollowup = hasFollowup;
	}
}