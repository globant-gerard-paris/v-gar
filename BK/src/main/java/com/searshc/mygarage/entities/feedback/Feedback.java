package com.searshc.mygarage.entities.feedback;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.entities.AbstractEntity;
import com.searshc.mygarage.entities.User;

/**
 * The Feedback represent an user opinion about the application.
 * It can be submitted at any time and as many times as the user wants. 
 */
@Entity
public class Feedback extends AbstractEntity{
	
	/**
	 * The {@link User} who submitted the comment.
	 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private User user;
	
	/**
	 * The comment provided as feedback.
	 */
	@Column(name = "comment", nullable = false)
	private String comment;
	
	public Feedback() {
	}
	
	public Feedback(final User user, final String comment) {
		this.user = Validate.notNull(user, "The User cannot be null");
		Validate.isTrue(!StringUtils.isEmpty(comment), "The comment cannot be null or empty");
		this.comment = comment;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = Validate.notNull(user, "The User cannot be null");
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
		Validate.isTrue(!StringUtils.isEmpty(comment), "The comment cannot be null or empty");
		this.comment = comment;
	}

}
