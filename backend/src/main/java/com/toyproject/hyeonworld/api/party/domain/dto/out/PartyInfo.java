package com.toyproject.hyeonworld.api.party.domain.dto.out;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/
@Getter
public class PartyInfo {
  long id;
  byte relationType;
  public static PartyInfo from (Party party) {
    return ObjectrMapper.convert(party, PartyInfo.class);
  }
}
