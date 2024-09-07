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
  private final long partyId;
  private final long roundId;

  private static Submission.SubmissionBuilder initializeEntity(){
    return Submission.builder();
  }

  public static Submission createEntity(long roundId, SubmissionCommand command) {
    return initializeEntity()
        .roundId(roundId)
        .userId(command.userId())
        .text(command.text())
        .number(command.number())
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
