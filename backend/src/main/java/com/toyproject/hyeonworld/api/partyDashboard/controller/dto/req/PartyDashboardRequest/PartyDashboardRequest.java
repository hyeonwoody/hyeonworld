package com.toyproject.hyeonworld.api.partyDashboard.controller.dto.req.PartyDashboardRequest;

import com.toyproject.hyeonworld.api.party.controller.dto.req.PartyRequest;
import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.in.ChangeDashboardCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record PartyDashboardRequest (
    Long gameId,
    Byte gameStage
) {
  public ChangeDashboardCommand toCommand(long partyId) {
    return new ChangeDashboardCommand(partyId, gameId, gameStage);
  }
}