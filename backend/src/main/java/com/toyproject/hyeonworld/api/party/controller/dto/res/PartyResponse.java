package com.toyproject.hyeonworld.api.party.controller.dto.res;

import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyDashboardInfo;
import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public abstract interface PartyResponse {
  record Begin (
      long id,
      byte relationType
  ) implements PartyResponse {
    public static PartyResponse.Begin from (PartyInfo partyInfo) {
      return ObjectrMapper.convert(partyInfo, Begin.class);
    }
  }

  record Dashboard (
    long gameId,
    byte stage
  ) implements PartyResponse {
    public static PartyResponse.Dashboard from (PartyDashboardInfo partyDashboardInfo) {
      return ObjectrMapper.convert(partyDashboardInfo, Dashboard.class);
    }
  }
}
