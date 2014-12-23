package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MemberPromptsReplyModel {
	@XmlElement(name="ResponseDate")
	private String responseDate;
	
	@XmlElement(name="ResponseTime")
	private String responseTime;
	
	@XmlElement(name="MemberNumber")
	private String memberNumber;
	
	@XmlElement(name="NumQuestionsReturned")
	private String numQuestionsReturned;
	
	@XmlElement(name="CheckoutPrompt")
	private CheckoutPromptModel CheckoutPrompt;

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public CheckoutPromptModel getCheckoutPrompt() {
		return CheckoutPrompt;
	}

	public void setCheckoutPrompt(CheckoutPromptModel checkoutPrompt) {
		CheckoutPrompt = checkoutPrompt;
	}
}