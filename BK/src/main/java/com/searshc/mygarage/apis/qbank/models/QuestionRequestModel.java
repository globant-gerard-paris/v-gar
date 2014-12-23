package com.searshc.mygarage.apis.qbank.models;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "GetMemberPrompts")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionRequestModel {
	@XmlElement(name = "MemberNumber")
	protected String memberNumber;

	@XmlElement(name = "MessageVersion")
	protected String messageVersion;

	@XmlElement(name = "RequestorID")
	protected String requestorID;

	@XmlElement(name = "PromptGroup")
	@XmlElementWrapper(name = "PromptGroups")
	protected List<QuestionPromptGroupModel> promptGroups = new ArrayList<QuestionPromptGroupModel>();
	
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

	public List<QuestionPromptGroupModel> getPromptGroups() {
		return promptGroups;
	}
	
	public void setPromptGroups(List<QuestionPromptGroupModel> promptGroups) {
		this.promptGroups = promptGroups;
	}

	public void AddPromptGroup(QuestionPromptGroupModel questionPromptGroupModel) {
		if (promptGroups == null)
			promptGroups = new ArrayList<QuestionPromptGroupModel>();
		promptGroups.add(questionPromptGroupModel);
	}
}