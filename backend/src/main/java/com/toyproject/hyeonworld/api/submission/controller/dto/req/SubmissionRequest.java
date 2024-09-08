package com.toyproject.hyeonworld.api.submission.controller.dto.req;


import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public abstract interface SubmissionRequest {
  record Basic(long partyId,
               long userId,
               String text,
               long number

  ) implements SubmissionRequest {
    public SubmissionCommand toCommand() {
      return new SubmissionCommand(partyId, userId, text, number);
    }
  }
}



