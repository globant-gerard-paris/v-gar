package com.searshc.mygarage.apis.qbank.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "FollowupQuestions")
@XmlAccessorType(XmlAccessType.FIELD)
public class FollowupQuestionResponseModel {
	@XmlElement(name = "FollowupAttributeID")
	private String followupAttributeID;
	@XmlElement(name = "FollowupQuestionTextID")
	private String followupQuestionTextID;

	@XmlElement(name = "AnswerOption")
	@XmlElementWrapper(name = "AnswerChoices")
	private List<AnswerChoiceModel> answerChoices = new ArrayList<AnswerChoiceModel>();

	public String getFollowupAttributeID() {
		return followupAttributeID;
	}

	public void setFollowupAttributeID(String followupAttributeID) {
		this.followupAttributeID = followupAttributeID;
	}

	public String getFollowupQuestionTextID() {
		return followupQuestionTextID;
	}

	public void setFollowupQuestionTextID(String followupQuestionTextID) {
		this.followupQuestionTextID = followupQuestionTextID;
	}

	public List<AnswerChoiceModel> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<AnswerChoiceModel> answerChoices) {
		this.answerChoices = answerChoices;
	}

	public void addAnswerChoice(AnswerChoiceModel answerChoiceModel) {
		if (answerChoices == null)
			answerChoices = new ArrayList<AnswerChoiceModel>();
		answerChoices.add(answerChoiceModel);
	}
}