package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCheckCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public record SubmissionCheckRequest (
    long roundId
) {

  public SubmissionCheckCommand toCommand(long roundId) {
    return new SubmissionCheckCommand(roundId);
  }
}