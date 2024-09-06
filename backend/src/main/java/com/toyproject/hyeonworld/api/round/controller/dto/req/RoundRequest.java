package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundAnswerCommand;
import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.session.controller.dto.req.SessionRequest;

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

  record Answer(
      int answer
  ) implements RoundRequest {

    public RoundAnswerCommand toCommand(long roundId){return new RoundAnswerCommand(roundId, answer); }

  }
}
