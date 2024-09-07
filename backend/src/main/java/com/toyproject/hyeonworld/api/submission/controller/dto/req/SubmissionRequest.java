package com.toyproject.hyeonworld.api.submission.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public record SubmissionRequest (
    long partyId,
    long userId,
    String text,
    long number
){
  public SubmissionCommand toCommand() {
    return new SubmissionCommand(partyId, userId, text, number);
  }
}

