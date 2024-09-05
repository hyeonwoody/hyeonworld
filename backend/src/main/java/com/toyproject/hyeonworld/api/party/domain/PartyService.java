package com.toyproject.hyeonworld.api.party.domain;

import static com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo.*;
import static com.toyproject.hyeonworld.api.party.infrastructure.entity.Party.*;

import com.toyproject.hyeonworld.api.party.domain.dto.in.ChangeDashboardCommand;
import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.api.party.domain.dto.in.OpenPartyCommand;

import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyDashboardInfo;
import com.toyproject.hyeonworld.api.party.infrastructure.PartyDashboardRepository;
import com.toyproject.hyeonworld.api.party.infrastructure.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/
@Service
@RequiredArgsConstructor
public class PartyService {
  private final PartyRepository partyRepository;

  public PartyInfo begin(OpenPartyCommand command) {
    return from(partyRepository.save(create(command)));
  }
}
