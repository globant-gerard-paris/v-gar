package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpsilonResponseModel {
	@XmlElement(name="Additional")
	private QuestionRequestAdditionalInfoModel additionalInfo;

	@XmlElement(name="MTServerName")
	private String mtServerName;
	
	@XmlElement(name="Response")
	private MemberPromptsResponseModel memberPromptsResponse;
	
	public QuestionRequestAdditionalInfoModel getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(QuestionRequestAdditionalInfoModel additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	public String getMTServerName() {
		return mtServerName;
	}

	public void setMTServerName(String mtServerName) {
		this.mtServerName = mtServerName;
	}
	
	public MemberPromptsResponseModel getMemberPromptsResponse() {
		return memberPromptsResponse;
	}

	public void setMemberPromptsResponse(MemberPromptsResponseModel memberPromptsResponse) {
		this.memberPromptsResponse = memberPromptsResponse;
	}
}