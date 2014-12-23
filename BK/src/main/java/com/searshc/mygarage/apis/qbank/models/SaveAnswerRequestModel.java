package com.searshc.mygarage.apis.qbank.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UpdateMemberPrompts")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveAnswerRequestModel {
	@XmlElement(name = "MemberNumber")
	private String memberNumber;

	@XmlElement(name = "MessageVersion")
	private String messageVersion;

	@XmlElement(name = "RequestorID")
	private String requestorID;
	
	@XmlElement(name = "POSPromptQuestions")
	@XmlElementWrapper(name = "POSPrompt")
	private List<POSPromptQuestionModel> promptQuestions = new ArrayList<POSPromptQuestionModel>(); 
	
	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public String getRequestorID() {
		return requestorID;
	}

	public void setRequestorID(String requestorID) {
		this.requestorID = requestorID;
	}

	public List<POSPromptQuestionModel> getPromptQuestions() {
		return promptQuestions;
	}

	public void setPromptQuestions(List<POSPromptQuestionModel> promptQuestions) {
		this.promptQuestions = promptQuestions;
	}
	
	public void addPromptQuestion(POSPromptQuestionModel posPromptQuestionModel) {
		if (promptQuestions == null)
			promptQuestions = new ArrayList<POSPromptQuestionModel>();
		promptQuestions.add(posPromptQuestionModel);
	}
}