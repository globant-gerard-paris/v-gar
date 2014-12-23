package com.searshc.mygarage.apis.qbank.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CheckoutPromptModel {
	@XmlElement(name = "CheckoutPromptQuestions")
	private List<CheckoutPromptQuestionsModel> checkoutPromptQuestions;

	public List<CheckoutPromptQuestionsModel> getCheckoutPromptQuestions() {
		return checkoutPromptQuestions;
	}

	public void setCheckoutPromptQuestions(List<CheckoutPromptQuestionsModel> checkoutPromptQuestions) {
		this.checkoutPromptQuestions = checkoutPromptQuestions;
	}
}