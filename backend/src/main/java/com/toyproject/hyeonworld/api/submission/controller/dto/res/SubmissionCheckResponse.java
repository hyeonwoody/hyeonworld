package com.toyproject.hyeonworld.api.submission.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.user.controller.dto.res.UserResponse;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public record SubmissionCheckResponse(
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
