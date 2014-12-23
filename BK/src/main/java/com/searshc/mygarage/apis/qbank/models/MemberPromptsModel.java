package com.searshc.mygarage.apis.qbank.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MemberPromptsModel {
	@XmlElement(name="Epsilon")
	private EpsilonResponseModel epsilonResponse;

	public EpsilonResponseModel getEpsilonResponse() {
		return epsilonResponse;
	}

	public void setEpsilonResponse(EpsilonResponseModel epsilonResponse) {
		this.epsilonResponse = epsilonResponse;
	}
}