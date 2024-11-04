package com.toyproject.hyeonworld.api.party.controller.dto.req;

import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyBeginRequest (
    byte relationType
) implements PartyRequest {
  public BeginPartyCommand toCommand() {
    return new BeginPartyCommand(relationType());
  }
}
