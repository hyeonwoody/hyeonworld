package com.toyproject.hyeonworld.api.user.domain.dto.out;

import java.util.List;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class UserWaitingListInfo {
  private final List<String> names;

  private UserWaitingListInfo(List<String> names) {
    this.names = names;
  }

  public static UserWaitingListInfo from(List<UserInGameInfo> users) {
    List<String> waitingListUserNames = users.stream()
        .filter(user->!user.isInGame())
        .map(UserInGameInfo::getName)
        .toList();
    return new UserWaitingListInfo(waitingListUserNames);
  }
}
