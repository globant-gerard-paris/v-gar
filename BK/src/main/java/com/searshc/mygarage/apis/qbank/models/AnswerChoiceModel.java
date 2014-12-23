package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "AnswerChoices")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerChoiceModel {
	@XmlElement(name="AnswerChoiceID")
	private String answerChoiceID;
	@XmlElement(name="AnswerTxt")
	private String answerTxt;
	
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
}