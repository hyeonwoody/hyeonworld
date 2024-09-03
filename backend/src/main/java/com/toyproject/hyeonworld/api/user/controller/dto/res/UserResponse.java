package com.toyproject.hyeonworld.api.user.controller.dto.res;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UserResponse (
  String name,
  Byte relationType,
  String nickname,
  Byte relation
) {

  public static UserResponse from (UserInfo userInfo){
    return ObjectrMapper.convert(userInfo, UserResponse.class);
  }
}
