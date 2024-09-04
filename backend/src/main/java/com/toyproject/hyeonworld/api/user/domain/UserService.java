package com.toyproject.hyeonworld.api.user.domain;

import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo.*;
import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos.*;
import static com.toyproject.hyeonworld.api.user.infrastructure.entity.User.*;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.EditUserCommand;

import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.api.user.infrastructure.UserRepository;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
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
        .orElseThrow(()->new ServerException(ServerResponseCode.USER_NOT_FOUND))
    );
  }

  @Transactional
  public UserInfo editUser(EditUserCommand command) {
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
    return from(userRepository.save(userInfo.entityToSession(true)));
  }

  public UserInfo confirmLogOut(long userId) {
    UserInfo userInfo = this.getUserById(userId);
    return from(userRepository.save(userInfo.entityToSession(false)));
  }
}
