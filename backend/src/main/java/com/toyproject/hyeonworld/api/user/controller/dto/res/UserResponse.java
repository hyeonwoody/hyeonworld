package com.toyproject.hyeonworld.api.user.controller.dto.res;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UserResponse (
  Long id,
  String name,
  Byte relationType,
  String nickname,
  Byte relation
) {
  public static List<UserResponse> from (UserInfos userInfos){
    return userInfos.stream()
        .map(UserResponse::from)
        .collect(Collectors.toList());
  }

  public static UserResponse from (UserInfo userInfo){
    return ObjectrMapper.convert(userInfo, UserResponse.class);
  }
}
