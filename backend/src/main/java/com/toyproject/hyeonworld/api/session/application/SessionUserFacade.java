package com.toyproject.hyeonworld.api.session.application;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.session.application.dto.in.SessionCommand;
import com.toyproject.hyeonworld.api.session.event.GameInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.GameOutSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogOutSessionEvent;
import com.toyproject.hyeonworld.api.session.event.SessionEventPublisher;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Facade
@RequiredArgsConstructor
public class SessionUserFacade {

  private static final Logger log = LoggerFactory.getLogger(SessionUserFacade.class);
  private final UserService userService;
  private final SessionEventPublisher eventPublisher;

  @Transactional
  public long createLoginSession(CreateLoginSessionCommand command) {
    UserInfo userInfo = userService.confirmLogin(command.userName());
    eventPublisher.execute(LogInSessionEvent.from(userInfo.getRelationType(), userInfo.getId(), userInfo.getName()));
    return userInfo.getId();
  }

  @Transactional
  public long deleteLoginSession(SessionCommand command) {
    UserInfo userInfo = userService.confirmLogOut(command.userId());
    eventPublisher.execute(LogOutSessionEvent.from(userInfo.getRelationType(), userInfo.getId(), userInfo.getName()));
    return command.userId();
  }

  @Transactional
  public long enterGame(SessionCommand command) {
    UserInfo userInfo = userService.confirmEnterGame(command.userId());
    eventPublisher.execute(GameInSessionEvent.from(userInfo.getRelationType(), userInfo.getId(), userInfo.getName()));
    return command.userId();
  }

  @Transactional
  public long exitGame(SessionCommand command) {
    UserInfo userInfo = userService.confirmExitGame(command.userId());
    eventPublisher.execute(GameOutSessionEvent.from(userInfo.getRelationType(), userInfo.getId(), userInfo.getName()));
    return command.userId();
  }
}