package com.toyproject.hyeonworld.api.submission.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SubmissionBasicResponse (
    long partyId,
    long roundId
) implements SubmissionResponse {

  public static SubmissionBasicResponse from(SubmissionInfo submissionInfo) {
    return ObjectrMapper.convert(submissionInfo, SubmissionBasicResponse.class);
  }
}