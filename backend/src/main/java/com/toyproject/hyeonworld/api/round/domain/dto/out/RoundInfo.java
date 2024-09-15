package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;

import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Getter
public class RoundInfo {
  long id;
  String answer;

  private static Round.RoundBuilder initializeEntity(){
    return Round.builder();
  }

  public static Round create (BeginRoundCommand command){
    return initializeEntity()
        .partyId(command.partyId())
        .gameId(command.gameId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static RoundInfo from (Round round) {
    return ObjectrMapper.convert(round, RoundInfo.class);
  }

  public static long getGameIdFrom (Round round) {
    return round.getGameId();
  }

  public static String getAnswerFrom (Round round) {
    return round.getAnswer();
  }

  public Round entityToUpdateAnswer(Object answer) {
    return initializeEntity()
        .id(this.id)
        .answer(String.valueOf(answer))
        .build();
  }
}
