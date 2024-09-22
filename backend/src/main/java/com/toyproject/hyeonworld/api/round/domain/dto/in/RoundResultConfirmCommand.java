package com.toyproject.hyeonworld.api.round.domain.dto.in;

import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 18.
 */
public record RoundResultConfirmCommand (
    Long partyId,
    Long roundId,
    List<Winner> winners
){
  public RoundResultConfirmCommand(long roundId, List<Winner> winners) {
    this(null, roundId, winners);
  }

  public record Winner(
      long id,
      long score
  ) {


  }
}
