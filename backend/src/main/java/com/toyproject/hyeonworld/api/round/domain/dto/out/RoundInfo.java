package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundAnswerCommand;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;

import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Getter
public class RoundInfo {
  long id;

  private static Round.RoundBuilder initializeEntity(){
    return Round.builder();
  }

  public static Round create (BeginRoundCommand command){
    return initializeEntity()
        .partyId(command.partyId())
        .gameId(command.gameId())
        .build();
  }

  public static RoundInfo from (Round round) {
    return ObjectrMapper.convert(round, RoundInfo.class);
  }

  public Round entityToUpdateAnswer(Object answer) {
    return initializeEntity()
        .id(this.id)
        .answer(String.valueOf(answer))
        .build();
  }
}
