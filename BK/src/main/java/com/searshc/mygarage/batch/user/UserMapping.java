package com.searshc.mygarage.batch.user;

/**
 * @author Jero
 *
 */
public class UserMapping {

	private String sywrId;
	private String ncdbId;

	public UserMapping() {
	}

	public UserMapping(String sywrId, String ncdbId) {
		this.sywrId = sywrId;
		this.ncdbId = ncdbId;
	}

	public String getSywrId() {
		return sywrId;
	}

	public void setSywrId(String sywrId) {
		this.sywrId = sywrId;
	}

	public String getNcdbId() {
		return ncdbId;
	}

	public void setNcdbId(String ncdbId) {
		this.ncdbId = ncdbId;
	}

}
