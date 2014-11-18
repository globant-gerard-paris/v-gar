package com.searshc.mygarage.apis.syw.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Jero
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYWUserResponse {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "sywrMemberNumber")
	private String sywrMemberNumber;

	@JsonProperty(value = "searsId")
	private Long searsId;

	@JsonProperty(value = "profileUrl")
	private String profileUrl;

	@JsonProperty(value = "registrationDate")
	private String registrationDate;

	@JsonProperty(value = "privacy")
	private String privacy;

	public SYWUserResponse() {
	}

	public SYWUserResponse(Long id, String name, String sywrMemberNumber, Long searsId,
			String profileUrl, String registrationDate, String privacy) {
		this.id = id;
		this.name = name;
		this.sywrMemberNumber = sywrMemberNumber;
		this.searsId = searsId;
		this.profileUrl = profileUrl;
		this.registrationDate = registrationDate;
		this.privacy = privacy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSywrMemberNumber() {
		return sywrMemberNumber;
	}

	public void setSywrMemberNumber(String sywrMemberNumber) {
		this.sywrMemberNumber = sywrMemberNumber;
	}

	public Long getSearsId() {
		return searsId;
	}

	public void setSearsId(Long searsId) {
		this.searsId = searsId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

}
