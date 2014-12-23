package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FollowupAnswerChoiceModel {
	@XmlElement(name="FollowupAnswerID")
	private String followupAnswerID;

	public String getFollowupAnswerID() {
		return followupAnswerID;
	}

	public void setFollowupAnswerID(String followupAnswerID) {
		this.followupAnswerID = followupAnswerID;
	}
}