package com.toyproject.hyeonworld.api.party.domain.dto.out;

import static com.toyproject.hyeonworld.api.party.infrastructure.entity.Party.*;

import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.time.LocalDateTime;
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
    if (party.getTerminatedAt() != null){
      throw new ServerException(ServerResponseCode.PARTY_ALREADY_TERMINATED);
    }
    return ObjectrMapper.convert(party, PartyInfo.class);
  }

  private static Party.PartyBuilder initializeEntity(){
    return Party.builder();
  }

  public static Party create (BeginPartyCommand command){
    return initializeEntity()
        .relationType(command.relationType())
        .build();
  }

  public Party entityToTerminate() {
    return initializeEntity()
        .id(this.id)
        .terminatedAt(LocalDateTime.now())
        .build();
  }

  public static PartyInfo fromADMIN (Party party) {
    return ObjectrMapper.convert(party, PartyInfo.class);
  }


}
