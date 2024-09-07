package com.toyproject.hyeonworld.api.submission.controller.dto.req;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCheckCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public record SubmissionCheckRequest (
    long partyId,
    long roundId
) {

  public SubmissionCheckCommand toCommand() {
    return new SubmissionCheckCommand(partyId, roundId);
  }
}