package com.toyproject.hyeonworld.api.party.controller.dto.req;

import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/

public sealed interface PartyRequest {
  record Begin (
      byte relationType
  ) implements PartyRequest {
    public BeginPartyCommand toCommand() {
      return new BeginPartyCommand(relationType());
    }
  }

}