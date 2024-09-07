package com.toyproject.hyeonworld.api.submission.interfaces;

import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import com.toyproject.hyeonworld.api.submission.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public interface SubmissionEventListener extends EventListener {
  void handleSubmissionEvent(SubmissionEvent event);

  void handleSubmissionBasicEvent(SubmissionEvent.Basic event);
}
