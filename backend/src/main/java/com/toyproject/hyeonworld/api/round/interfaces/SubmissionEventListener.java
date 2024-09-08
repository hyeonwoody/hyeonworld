package com.toyproject.hyeonworld.api.round.interfaces;

import com.toyproject.hyeonworld.api.round.event.SubmissionEvent;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public interface SubmissionEventListener extends EventListener {
  void handleSubmissionEvent(SubmissionEvent event);

  void handleSubmissionBasicEvent(SubmissionEvent.Basic event);
}