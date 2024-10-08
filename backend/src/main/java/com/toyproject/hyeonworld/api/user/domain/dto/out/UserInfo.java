package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.user.domain.dto.in.UpdateUserCommand;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.Objects;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Getter
public class UserInfo {
  Long id;
  String name;
  Byte relationType;
  String nickname;
  Byte relation;
  boolean login;
  boolean inGame;

  private User.UserBuilder initializeEntity(){
    return User.builder()
        .id(this.id)
        .name(this.name)
        .relationType(this.relationType)
        .nickname(this.nickname)
        .login(this.login)
        .inGame(this.inGame)
        .relation(this.relation);
  }

  public static UserInfo from (User user) {
    return ObjectrMapper.convert(user, UserInfo.class);
  }

  public static UserInfo fromDelete (int ret) {
    if (ret == 1) {
      return new UserInfo();
    }
    throw new ServerException(ServerResponseCode.USER_NOT_FOUND);
  }

  public User toEntity() {
    return initializeEntity()
        .build();
  }

  public User entityToInit() {
    return initializeEntity()
        .login(false)
        .inGame(false)
        .build();
  }

  public User entityToSession(boolean login) {
    return initializeEntity()
        .login(login)
        .inGame(false)
        .build();
  }

  public User entityToGame(boolean inGame) {
    return initializeEntity()
        .login(true)
        .inGame(inGame)
        .build();
  }

  public void update(UpdateUserCommand command) {
    command.name().ifPresent(newName -> {
      if (!Objects.equals(this.name, newName)) {
        this.name = newName;
      }
    });

    command.relationType().ifPresent(newRelationType -> {
      if (this.relationType != newRelationType) {
        this.relationType = newRelationType;
      }
    });

    command.relation().ifPresent(newRelation -> {
      if (this.relation != newRelation) {
        this.relation = newRelation;
      }
    });
  }
}
