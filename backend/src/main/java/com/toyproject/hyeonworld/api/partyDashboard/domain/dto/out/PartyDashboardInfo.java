package com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out;

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
}
