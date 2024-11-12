package com.toyproject.hyeonworld.api.round.domain.dto.in;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 18.
 */
public record RoundResultConfirmCommand (
    long roundId,
    List<Winner> winners
){

  public record Winner(
      long id,
      long score
  ) {


  }

  public static RoundResultConfirmCommand from (long roundId, List<Winner> winners) {
    return new RoundResultConfirmCommand(roundId, winners);
  }
}
