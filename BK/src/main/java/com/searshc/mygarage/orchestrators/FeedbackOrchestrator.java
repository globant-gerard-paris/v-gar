package com.searshc.mygarage.orchestrators;

import com.searshc.mygarage.entities.feedback.Feedback;

public interface FeedbackOrchestrator {

	/**
	 * Creates a {@link Feedback} based on an user opinion.
	 * @param userId the submitting user id
	 * @param comment the user opinion
	 */
	void submitFeedback(final Long userId, final String comment);

}
