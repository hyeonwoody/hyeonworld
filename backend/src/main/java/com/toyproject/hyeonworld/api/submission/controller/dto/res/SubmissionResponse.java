package com.toyproject.hyeonworld.api.submission.controller.dto.res;

import com.toyproject.hyeonworld.api.session.controller.dto.res.SessionResponse;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public record SubmissionResponse (
    long partyId,
    long roundId
    ){
  public static SubmissionResponse from(SubmissionInfo submissionInfo) {
    return ObjectrMapper.convert(submissionInfo, SubmissionResponse.class);
  }
}
