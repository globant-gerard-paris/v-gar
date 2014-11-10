/**
 * 
 */
package com.searshc.mygarage.entities;

import java.util.Date;

import javax.persistence.Entity;

/**
 * 
 * The {@link Record} is the historic registered services that were performed in the car.
 * 
 * @author Jero
 *
 */
@Entity
public class Record extends AbstractEntity {

	/**
	 * The {@code date} of the record.
	 */
	private Date date;

	/**
	 * The description of the {@code service} of the record.
	 */
	private String service;

	/**
	 * A technical detail of what was done to the car.
	 */
	private String comment;

	private String source;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

}
