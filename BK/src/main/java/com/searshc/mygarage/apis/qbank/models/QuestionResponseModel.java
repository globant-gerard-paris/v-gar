package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetMemberPromptsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionResponseModel {
	@XmlElement(name="GetMemberPromptsResult")
	private MemberPromptsModel memberPrompts;

	public MemberPromptsModel getMemberPrompts() {
		return memberPrompts;
	}

	public void setmemberPrompts(MemberPromptsModel memberPrompts) {
		this.memberPrompts = memberPrompts;
	}
}