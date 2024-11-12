package com.toyproject.hyeonworld.api.user.domain;

import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos.*;
import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo.*;
import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserWaitingListDto.*;
import static com.toyproject.hyeonworld.api.user.infrastructure.entity.User.*;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.RetrieveUserWaitingListCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.UpdateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.out.NameInGameInfos;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserWaitingListDto;
import com.toyproject.hyeonworld.api.user.infrastructure.UserRepository;
import com.toyproject.hyeonworld.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserInfo createUser(CreateUserCommand command) {
    return from(userRepository.save(create(command)));
  }

  public UserInfos getAllUsers() {
    return from(userRepository.findAll());
  }

  public UserInfo getUserById(long userId) {
    return from(userRepository.findById(userId)
        .orElseThrow(()->new ServerException(ServerResponseCode.USER_NOT_FOUND, String.valueOf(userId)))
    );
  }

  @Transactional
  public UserInfo updateUser(UpdateUserCommand command) {
    UserInfo userInfo = this.getUserById(command.userId());
    userInfo.update(command);
    return from(userRepository.save(userInfo.toEntity()));
  }

  @Transactional
  public UserInfo deleteUser(long userId) {
    return fromDelete(userRepository.deleteById(userId));
  }

  public int initUsers() {
    UserInfos userInfos = UserInfos.from(userRepository.findByLogin(true));
    userInfos.forEach(userInfo -> userRepository.save(userInfo.entityToInit()));
    return userInfos.size();
  }

  public UserInfo getUserByName(String userName) {
    return from(userRepository.findByName(userName)
        .orElseThrow(()->new ServerException(ServerResponseCode.USER_NOT_FOUND))
    );
  }

  public UserInfo confirmLogin(String userName) {
    UserInfo userInfo = this.getUserByName(userName);
    if (userInfo.isLogin()){
      throw new ServerException(ServerResponseCode.USER_ALREADY_LOGGED_IN);
    }
    return from(userRepository.save(userInfo.entityToSession(true)));
  }

  public UserInfo confirmLogOut(long userId) {
    UserInfo userInfo = this.getUserById(userId);
    return from(userRepository.save(userInfo.entityToSession(false)));
  }

  public UserInfo confirmEnterGame(long userId) {
    UserInfo userInfo = this.getUserById(userId);
    return from(userRepository.save(userInfo.entityToGame(true)));
  }

  public UserInfo confirmExitGame(long userId) {
    UserInfo userInfo = this.getUserById(userId);
    return from(userRepository.save(userInfo.entityToGame(false)));
  }

  public List<String> retrieveWaitingList(RetrieveUserWaitingListCommand command) {
    NameInGameInfos nameInGameInfos = NameInGameInfos.from(userRepository.findByLogin(true));
    return nameInGameInfos.getWatingNameList();
  }

  public String getNameById(long userId) {
    UserInfo userInfo = this.getUserById(userId);
    return userInfo.getName();
  }

  public Map<Long, String> getNamesByIds(Set<Long> userIds) {
    if (userIds.isEmpty()){
      return Collections.emptyMap();
    }
    return userRepository.findNamesByIds(userIds).stream()
        .collect(Collectors.toMap(
            UserNameProjection::getId,
            UserNameProjection::getName,
            (existing, replacement)-> existing, LinkedHashMap::new
        ));
  }


}
