package com.toyproject.hyeonworld.api.partyDashboard.controller.dto.res;

import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record PartyDashboardResponse (
      long currentGameId,
      byte currentGameStage
  ) {

  public static PartyDashboardResponse from(PartyDashboardInfo partyDashboardInfo) {
    return ObjectrMapper.convert(partyDashboardInfo, PartyDashboardResponse.class);
  }
}
