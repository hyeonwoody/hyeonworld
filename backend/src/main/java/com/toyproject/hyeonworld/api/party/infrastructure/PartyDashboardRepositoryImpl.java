package com.toyproject.hyeonworld.api.party.infrastructure;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.PartyDashboard;
import com.toyproject.hyeonworld.api.party.infrastructure.jpa.PartyDashboardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Repository
@RequiredArgsConstructor
public class PartyDashboardRepositoryImpl implements PartyDashboardRepository{
  private final PartyDashboardJpaRepository partyDashboardJpaRepository;

  @Override
  public PartyDashboard save(PartyDashboard partyDashboard){
    return partyDashboardJpaRepository.save(partyDashboard);
  }

  @Override
  public PartyDashboard saveAndFlush(PartyDashboard partyDashboard) {
    return partyDashboardJpaRepository.saveAndFlush(partyDashboard);
  }
}
