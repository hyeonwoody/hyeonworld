package com.toyproject.hyeonworld.api.round.interfaces;


import com.toyproject.hyeonworld.api.round.event.Submission.AnswerSubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.PrimarySubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.SubmissionEvent;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public interface SubmissionEventListener extends EventListener {
  void handleSubmissionEvent(SubmissionEvent event);

  void handleAnswerSubmissionEvent(AnswerSubmissionEvent event);

  void handlePrimarySubmissionEvent(PrimarySubmissionEvent event);


}
