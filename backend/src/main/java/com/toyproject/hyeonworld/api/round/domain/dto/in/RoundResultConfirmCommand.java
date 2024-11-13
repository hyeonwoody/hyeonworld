package com.toyproject.hyeonworld.api.round.domain.dto.in;

import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 18.
 */
public record RoundResultConfirmCommand (
    long roundId,
    List<Participant> participants
){

  public record Participant(
      long id,
      long score
  ) {


  }

  public static RoundResultConfirmCommand from (long roundId, List<Participant> participants) {
    return new RoundResultConfirmCommand(roundId, participants);
  }
}
