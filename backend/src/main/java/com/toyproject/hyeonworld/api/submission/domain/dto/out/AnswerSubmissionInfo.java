package com.toyproject.hyeonworld.api.submission.domain.dto.out;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.AnswerSubmission;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import jakarta.persistence.Column;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
public class AnswerSubmissionInfo {
  private Long id;
  private Long roundId;
  private Long userId;
  private String answer;

  public static AnswerSubmission createEntity(long roundId, RoundPlayCommand command){
    return AnswerSubmission.builder()
        .roundId(roundId)
        .userId(command.userId())
        .answer(command.answer())
        .build();
  }

  public static AnswerSubmissionInfo from(AnswerSubmission answerSubmission) {
    return ObjectrMapper.convert(answerSubmission, AnswerSubmissionInfo.class);
  }
}
