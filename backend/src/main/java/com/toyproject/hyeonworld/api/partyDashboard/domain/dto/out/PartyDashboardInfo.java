package com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out;

import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */

public class PartyDashboardInfo {
  private Long partyid;
  private Long currentGameId;
  private Byte currentGameStage;
  public static PartyDashboardInfo from (PartyDashboard partyDashboard) {
    return ObjectrMapper.convert(partyDashboard, PartyDashboardInfo.class);
  }
}
