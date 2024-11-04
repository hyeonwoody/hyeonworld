package com.toyproject.hyeonworld.api.session.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.SessionCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SessionDeleteRequest(
    long userId
) implements SessionRequest {
  public SessionCommand toCommand() {
    return new SessionCommand(userId);
  }
}