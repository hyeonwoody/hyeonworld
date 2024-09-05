package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
@AllArgsConstructor
public class UserInGameInfo {
  private final String name;
  private final boolean inGame;

    public static List<UserInGameInfo> from(List<User> users) {
      return users.stream()
          .map(user -> new UserInGameInfo(user.getName(), user.getInGame()))
          .toList();
    }
}
