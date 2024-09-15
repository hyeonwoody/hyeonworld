package com.toyproject.hyeonworld.api.submission.controller.dto.res;

import com.toyproject.hyeonworld.api.session.controller.dto.res.SessionResponse;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionRequest;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public abstract interface SubmissionResponse {

  record Basic(
      long partyId,
      long roundId
  ) implements SubmissionResponse {

    public static SubmissionResponse.Basic from(SubmissionInfo submissionInfo) {
      return ObjectrMapper.convert(submissionInfo, SubmissionResponse.Basic.class);
    }
  }
};