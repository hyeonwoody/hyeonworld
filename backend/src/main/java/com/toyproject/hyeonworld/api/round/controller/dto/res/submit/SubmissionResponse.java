package com.toyproject.hyeonworld.api.round.controller.dto.res.submit;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SubmissionResponse(
    long partyId,
    long roundId
) implements RoundSubmitResponse {

  public static SubmissionResponse from(SubmissionInfo submissionInfo) {
    return ObjectrMapper.convert(submissionInfo, SubmissionResponse.class);
  }
}