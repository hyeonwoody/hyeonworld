package com.toyproject.hyeonworld.api.session.controller.dto.res;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SessionBasicResponse (
    long userId
) implements SessionResponse {

  public static SessionBasicResponse from(long userId) {
    return new SessionBasicResponse(userId);
  }
}