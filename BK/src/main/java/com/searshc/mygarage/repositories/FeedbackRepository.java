package com.searshc.mygarage.repositories;

import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.feedback.Feedback;

@Repository
public interface FeedbackRepository extends GenericRepository<Feedback, Long> {

}
