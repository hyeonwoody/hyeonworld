package com.toyproject.hyeonworld.api.round.event;


import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public interface SubmissionEvent extends CustomEvent {
  record Basic(long roundId, SubmissionCommand command) implements SubmissionEvent {}

  record Answer(RoundPlayCommand command) implements SubmissionEvent {}
}
