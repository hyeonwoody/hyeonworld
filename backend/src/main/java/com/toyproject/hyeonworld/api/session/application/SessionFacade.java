package com.toyproject.hyeonworld.api.session.application;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.session.event.LoginEvent;
import com.toyproject.hyeonworld.api.session.event.LoginEventPublisher;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Facade
@RequiredArgsConstructor
public class SessionFacade {
  private final UserService userService;
  private final LoginEventPublisher loginEventPublisher;

  @Transactional
  public long createLoginSession(CreateLoginSessionCommand command) {
    UserInfo userInfo = userService.confirmLogin(command.userName());
    loginEventPublisher.execute(new LoginEvent(userInfo.getId(), userInfo.getName()));
    return userInfo.getId();
  }
}
