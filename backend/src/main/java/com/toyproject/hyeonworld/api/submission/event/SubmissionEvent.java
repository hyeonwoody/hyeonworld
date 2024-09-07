package com.toyproject.hyeonworld.api.submission.event;

import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public interface SubmissionEvent extends CustomEvent {
  record Basic(long roundId, SubmissionCommand command) implements SubmissionEvent {}
}
