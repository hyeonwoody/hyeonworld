package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfo;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
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
}
