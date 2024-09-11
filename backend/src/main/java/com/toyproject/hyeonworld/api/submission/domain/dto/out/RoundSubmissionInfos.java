package com.toyproject.hyeonworld.api.submission.domain.dto.out;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public class RoundSubmissionInfos extends ArrayList<RoundSubmissionInfo> {

  private RoundSubmissionInfos(List<RoundSubmissionInfo> roundSubmissionInfos){
    super(roundSubmissionInfos != null ? roundSubmissionInfos : Collections.emptyList());
  }

  public static RoundSubmissionInfos from(List<Submission> submission) {
    return new RoundSubmissionInfos(submission.stream()
        .map(RoundSubmissionInfo::from)
        .toList());
  }
}
