package com.searshc.mygarage.dtos.feedback;

/**
 * This DTO is aimed to be able to include additional fields in the future
 * @author daniel.talebi
 *
 */
public class FeedbackDTO {

	private String comment;
	
	public FeedbackDTO() {
	}

	/**
	 * @param comment
	 */
	public FeedbackDTO(String comment) {
		super();
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
