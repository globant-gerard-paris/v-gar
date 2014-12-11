package com.searshc.mygarage.services.feedback;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.feedback.Feedback;
import com.searshc.mygarage.repositories.FeedbackRepository;

@Service
public class FeedbackService extends GenericService<Feedback, Long, FeedbackRepository>{

}
