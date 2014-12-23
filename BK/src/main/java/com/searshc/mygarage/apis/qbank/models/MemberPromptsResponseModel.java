package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MemberPromptsResponseModel {
	@XmlElement(name="GetMemberPromptsReply")
	private MemberPromptsReplyModel memberPromptsReply;

	public MemberPromptsReplyModel getMemberPromptsReply() {
		return memberPromptsReply;
	}

	public void setMemberPromptsReply(MemberPromptsReplyModel memberPromptsReply) {
		this.memberPromptsReply = memberPromptsReply;
	}
}