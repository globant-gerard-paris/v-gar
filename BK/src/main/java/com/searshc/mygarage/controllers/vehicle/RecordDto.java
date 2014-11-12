package com.searshc.mygarage.controllers.vehicle;

import java.util.Date;

/**
 * @author Jero
 *
 */
public class RecordDto {

	private Date date;
	private ServiceRecordDto service;
	private String comment;
	private String source;
	private String mileage;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ServiceRecordDto getService() {
		return service;
	}

	public void setService(ServiceRecordDto service) {
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

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "RecordDto [date=" + date + ", service=" + service + ", comment=" + comment
				+ ", source=" + source + ", mileage=" + mileage + "]";
	}

}
