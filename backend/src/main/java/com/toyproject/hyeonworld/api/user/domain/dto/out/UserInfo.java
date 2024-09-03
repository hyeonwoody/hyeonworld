package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfo;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.user.domain.dto.in.EditUserCommand;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
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
  public static UserInfo from (User user) {
    return ObjectrMapper.convert(user, UserInfo.class);
  }

  public User toEntity() {
    return User.builder()
        .id(this.id)
        .name(this.name)
        .relationType(this.relationType)
        .nickname(this.nickname)
        .relation(this.relation)
        .build();
  }

  public void update(EditUserCommand command) {
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
