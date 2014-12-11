package com.searshc.mygarage.orchestrators;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.feedback.Feedback;

/**
 * The {@link FeedbackOrchestrator} implementation.
 * @author daniel.talebi
 *
 */
@Component
public class FeedbackOrchestratorImpl extends BaseOrchestrator implements FeedbackOrchestrator {

	@Override
	public void submitFeedback(final Long userId, final String comment) {
		Validate.notNull(userId, "The UserId cannot be null");
		Validate.isTrue(!StringUtils.isEmpty(comment), "The comment cannot be null or empty");
		
		User user = this.userService.getItem(userId);
		Feedback feedback = new Feedback(user, comment);
		this.feedbackService.save(feedback);
	}
}
