package com.toyproject.hyeonworld.api.submission.domain.dto.out;


import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import lombok.Builder;
import lombok.Getter;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Getter
@Builder
public class SubmissionInfo {
  private long id;
  private final long partyId;
  private final long roundId;
  private String text;
  private Long number;

  private static Submission.SubmissionBuilder initializeEntity(){
    return Submission.builder();
  }

  public static Submission toEntity(long roundId, SubmissionCommand command) {
    return initializeEntity()
        .roundId(roundId)
        .userId(command.userId())
        .text(command.text())
        .number(command.number())
        .build();
  }
  public static SubmissionInfo from (Submission submission) {
    return SubmissionInfo.builder()
        .id(submission.getId())
        .roundId(submission.getRoundId())
            .text(submission.getText())
            .number(submission.getNumber())
        .build();
  }

  public static SubmissionInfo from (long partyId, Submission submission) {
    return SubmissionInfo.builder()
        .partyId(partyId)
        .roundId(submission.getRoundId())
        .build();
  }

  public static SubmissionInfo from (long roundId, SubmissionCommand command) {
    return SubmissionInfo.builder()
        .partyId(command.partyId())
        .roundId(roundId)
        .build();
  }
}
