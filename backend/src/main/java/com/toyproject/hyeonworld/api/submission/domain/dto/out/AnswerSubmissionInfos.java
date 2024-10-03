package com.toyproject.hyeonworld.api.submission.domain.dto.out;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.AnswerSubmission;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
public class AnswerSubmissionInfos extends ArrayList<AnswerSubmissionInfo>{

  private AnswerSubmissionInfos (List<AnswerSubmissionInfo> answerSubmissionInfos){
    super(answerSubmissionInfos != null ? answerSubmissionInfos : Collections.emptyList());
  }

  public static AnswerSubmissionInfos from (List<AnswerSubmission> answerSubmission){
    return new AnswerSubmissionInfos(answerSubmission.stream()
        .map(AnswerSubmissionInfo::from)
        .toList());
  }

}