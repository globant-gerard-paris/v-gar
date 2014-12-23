package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "UpdateMemberPromptsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveAnswerResponseModel {
	@XmlElement(name = "UpdateMemberPromptsResult")
	private MemberPromptsModel memberPromptsModel;

	public MemberPromptsModel getMemberPromptsModel() {
		return memberPromptsModel;
	}

	public void setMemberPromptsModel(MemberPromptsModel memberPromptsModel) {
		this.memberPromptsModel = memberPromptsModel;
	}
}