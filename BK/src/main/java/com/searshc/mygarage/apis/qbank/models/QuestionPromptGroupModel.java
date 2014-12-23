package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PromptGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionPromptGroupModel {
	@XmlElement(name = "MaxPOSPrompt")
	protected String maxPOSPrompt;
	@XmlElement(name = "PromptGroupName")
	protected String promptGroupName;
	
	public String getMaxPOSPrompt() {
		return maxPOSPrompt;
	}
	public void setMaxPOSPrompt(String maxPOSPrompt) {
		this.maxPOSPrompt = maxPOSPrompt;
	}

	public String getPromptGroupName() {
		return promptGroupName;
	}
	public void setPromptGroupName(String promptGroupName) {
		this.promptGroupName = promptGroupName;
	}
}