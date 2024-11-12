package com.toyproject.hyeonworld.api.user.domain.dto.out;

import java.util.List;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class UserWaitingListDto {
  private final List<String> names;

  private UserWaitingListDto(List<String> names) {
    this.names = names;
  }

  public static UserWaitingListDto from(NameInGameInfos users) {
    return new UserWaitingListDto(users.getWatingNameList());
  }
}
