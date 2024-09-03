package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfo;
import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public class UserInfos extends ArrayList<UserInfo> {

  public UserInfos (List<UserInfo> userInfos) {
    super(userInfos != null ? userInfos : Collections.emptyList());
  }

  public static UserInfos from(List<User> users) {
    return new UserInfos(users.stream()
        .map(UserInfo::from)
        .collect(Collectors.toList()));
  }
}