package com.toyproject.hyeonworld.api.party.controller.dto.res;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyTerminateResponse (
    long id
) implements PartyResponse {
  public static PartyTerminateResponse from (long partyId) {
    return new PartyTerminateResponse(partyId);
  }
}
