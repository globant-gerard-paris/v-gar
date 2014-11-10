package com.searshc.mygarage.controllers.vehicle;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Jero
 *
 */
public class RecordDto {

	private String date;
	private String service;
	private String comment;
	private String source;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "RecordDto [date=" + date + ", service=" + service + ", comment=" + comment
				+ ", source=" + source + "]";
	}

}
