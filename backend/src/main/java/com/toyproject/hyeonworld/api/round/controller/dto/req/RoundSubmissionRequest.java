package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionRequest;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.RoundSubmissionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public abstract interface RoundSubmissionRequest extends SubmissionRequest {

  record Basic(
      long roundId
  ) implements RoundSubmissionRequest {

    public RoundSubmissionCommand toCommand(long roundId) {
      return new RoundSubmissionCommand(roundId);
    }
  }


  record Confirm(
      long roundId,
      long gameId,
      long userId,
      String text,
      long number
  )implements RoundSubmissionRequest {

    public SubmissionCheckConfirmCommand toCommand(long roundId) {
      return new SubmissionCheckConfirmCommand(roundId, gameId, userId, text, number);
    }
  }
}