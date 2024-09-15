package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public abstract interface RoundRequest {
  record Begin(long partyId,
    long gameId

  ) implements RoundRequest {
    public BeginRoundCommand toCommand() {
      return new BeginRoundCommand(partyId, gameId);
    }
  }

  record Play(
      long partyId,
      long userId,
      String answer
  ) implements RoundRequest {

    public RoundPlayCommand toCommand(){return new RoundPlayCommand(partyId, userId, answer); }

  }
}
