package com.toyproject.hyeonworld.api.session.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.session.application.dto.in.SessionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public sealed interface SessionRequest {
  record Create(String userName) implements SessionRequest {
    public CreateLoginSessionCommand toCommand() {
      return new CreateLoginSessionCommand(userName);
    }
  }

  record Delete(long userId) implements SessionRequest {
    public SessionCommand toCommand() {
      return new SessionCommand(userId);
    }
  }

  record Game(long userId) implements SessionRequest {
    public SessionCommand toCommand() {
      return new SessionCommand(userId);
    }
  }
}
