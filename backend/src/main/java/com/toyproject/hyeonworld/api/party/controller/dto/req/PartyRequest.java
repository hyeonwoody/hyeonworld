package com.toyproject.hyeonworld.api.party.controller.dto.req;

import com.toyproject.hyeonworld.api.party.domain.dto.in.ChangeDashboardCommand;
import com.toyproject.hyeonworld.api.party.domain.dto.in.OpenPartyCommand;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/

public sealed interface PartyRequest {
  record Begin (
      byte relationType
  ) implements PartyRequest {
    public OpenPartyCommand toCommand() {
      return new OpenPartyCommand(relationType());
    }
  }

}