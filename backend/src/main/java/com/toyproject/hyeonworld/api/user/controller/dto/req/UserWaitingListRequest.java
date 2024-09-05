package com.toyproject.hyeonworld.api.user.controller.dto.req;

import com.toyproject.hyeonworld.api.user.domain.dto.in.RetrieveUserWaitingListCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record UserWaitingListRequest(
    long partyId
) implements UserRequest {
  public RetrieveUserWaitingListCommand toCommand() {
    return new RetrieveUserWaitingListCommand(partyId);
  }
}
