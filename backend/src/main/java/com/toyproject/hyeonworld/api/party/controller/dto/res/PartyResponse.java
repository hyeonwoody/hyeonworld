package com.toyproject.hyeonworld.api.party.controller.dto.res;

import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.api.user.controller.dto.req.UserRequest;
import com.toyproject.hyeonworld.api.user.controller.dto.res.UserResponse;
import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public abstract interface PartyResponse {
  record Begin (
      long id,
      byte relationType
  ) implements PartyResponse {
    public static PartyResponse.Begin from (PartyInfo partyInfo) {
      return ObjectrMapper.convert(partyInfo, Begin.class);
    }
  }
}
