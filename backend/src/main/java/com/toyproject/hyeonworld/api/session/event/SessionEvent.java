package com.toyproject.hyeonworld.api.session.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public abstract class SessionEvent implements CustomEvent {
  @Getter
  private final long userId;

  protected SessionEvent(long userId) {
    this.userId = userId;
  }

  @Getter
  public static class Login extends SessionEvent {
    private final String userName;
    public Login(long userId, String userName) {
      super(userId);
      this.userName = userName;
    }
  }

  @Getter
  public static class Logout extends SessionEvent {
    public Logout(long userId) {
      super(userId);
    }
  }
}
