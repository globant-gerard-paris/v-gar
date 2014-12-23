package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerOptionModel {
	@XmlElement(name="AnswerChoiceID")
	private String answerChoiceID;
	
	@XmlElement(name="ButtonTxt")
	private String buttonTxt; 
	
	@XmlElement(name="AnswerTxt")
	private String answerTxt;

	public String getAnswerChoiceID() {
		return answerChoiceID;
	}

	public void setAnswerChoiceID(String answerChoiceID) {
		this.answerChoiceID = answerChoiceID;
	}

	public String getButtonTxt() {
		return buttonTxt;
	}

	public void setButtonTxt(String buttonTxt) {
		this.buttonTxt = buttonTxt;
	}

	public String getAnswerTxt() {
		return answerTxt;
	}

	public void setAnswerTxt(String answerTxt) {
		this.answerTxt = answerTxt;
	}
}