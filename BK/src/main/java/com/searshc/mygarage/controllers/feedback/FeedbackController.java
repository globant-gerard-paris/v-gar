package com.searshc.mygarage.controllers.feedback;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searshc.mygarage.dtos.feedback.FeedbackDTO;
import com.searshc.mygarage.orchestrators.FeedbackOrchestrator;

/**
 * The Feedback Controller
 * @author daniel.talebi
 *
 */
@Controller
@RequestMapping("feedback")
public class FeedbackController {

	private FeedbackOrchestrator feedbackOrchestrator;
	
	@Inject
	public FeedbackController(final FeedbackOrchestrator feedbackOrchestrator) {
		this.feedbackOrchestrator = Validate.notNull(feedbackOrchestrator, "The Feedback Orchestrator cannot be null");
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> submitFeedback(@PathVariable("userId") final Long userId,
			@RequestBody final FeedbackDTO feedbackDto) {
		this.feedbackOrchestrator.submitFeedback(userId, feedbackDto.getComment());
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}
