package com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out;

import static com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard.defaultBuilder;

import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.in.ChangeDashboardCommand;
import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class PartyDashboardInfo {
  private Long partyId;
  private Long currentGameId;
  private Byte currentGameStage;
  public static PartyDashboardInfo from (PartyDashboard partyDashboard) {
    return ObjectrMapper.convert(partyDashboard, PartyDashboardInfo.class);
  }

  public static PartyDashboard toEntity (long partyId){
    return defaultBuilder()
            .partyId(partyId)
            .currentGameId((long) -1)
            .currentGameStage((byte) -1)
            .build();
  }

  public static PartyDashboard toEntity (ChangeDashboardCommand command){
    return defaultBuilder()
            .partyId(command.partyId())
            .currentGameId(command.gameId())
            .currentGameStage(command.gameStage())
            .build();
  }
}
