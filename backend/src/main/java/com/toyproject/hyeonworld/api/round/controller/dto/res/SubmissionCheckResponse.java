package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public record SubmissionCheckResponse(
    long userId,
    String name,
    String text,
    String number
) {


  public static List<SubmissionCheckResponse> from(SubmissionCheckInfos submissionCheckInfos) {
    return submissionCheckInfos.stream()
        .map(SubmissionCheckResponse::from)
        .toList();
  }

  public static SubmissionCheckResponse from(SubmissionCheckInfo submissionCheckInfo) {
    return ObjectrMapper.convert(submissionCheckInfo, SubmissionCheckResponse.class);
  }
}
