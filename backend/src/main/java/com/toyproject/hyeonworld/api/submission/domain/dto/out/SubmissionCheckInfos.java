package com.toyproject.hyeonworld.api.submission.domain.dto.out;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public class SubmissionCheckInfos extends ArrayList<SubmissionCheckInfo> {

  private SubmissionCheckInfos (List<SubmissionCheckInfo> submissionCheckInfos){
    super(submissionCheckInfos!= null ? submissionCheckInfos : Collections.emptyList());
  }

  public static SubmissionCheckInfos from(List<Submission> submission) {
    return new SubmissionCheckInfos(submission.stream()
        .map(SubmissionCheckInfo::from)
        .toList());
  }
}
