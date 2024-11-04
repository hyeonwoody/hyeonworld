package com.toyproject.hyeonworld.api.submission.controller.dto.req;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SubmissionBasicRequest (long partyId,
                    long userId,
                    String text,
                    long number

) implements SubmissionRequest {
  public SubmissionCommand toCommand() {
    return new SubmissionCommand(partyId, userId, text, number);
  }
}
