package com.toyproject.hyeonworld.api.party.controller.dto.res;

import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyBeginResponse(
    long id,
    byte relationType
) implements PartyResponse {

  public static PartyBeginResponse from (PartyInfo partyInfo) {
    return ObjectrMapper.convert(partyInfo, PartyBeginResponse.class);
  }
}
