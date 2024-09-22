package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Result;
import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand.Winner;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public RoundPlayCommand toCommand() {
      return new RoundPlayCommand(partyId, userId, answer);
    }

  }


  record ResultScore(
      List<Winner> winners
  ) {
    record Winner(
        long id,
        long score
    ) {


    }
    public RoundResultConfirmCommand toCommand(long roundId) {
      List<RoundResultConfirmCommand.Winner> convertedWinners = this.winners.stream()
          .map(winner -> new RoundResultConfirmCommand.Winner(winner.id(), winner.score()))
          .collect(Collectors.toList());

      return new RoundResultConfirmCommand(roundId, convertedWinners);
    }
  }
}
