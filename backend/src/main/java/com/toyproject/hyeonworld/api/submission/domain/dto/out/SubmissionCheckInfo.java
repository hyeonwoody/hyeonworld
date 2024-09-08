package com.toyproject.hyeonworld.api.submission.domain.dto.out;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
@Getter
public class SubmissionCheckInfo {
  long userId;
  String name;
  String text;
  int number;

  public static SubmissionCheckInfo from(Submission submission) {
    return ObjectrMapper.convert(submission, SubmissionCheckInfo.class);
  }

  public void complete(String userName) {
    this.name = userName;
  }
}
