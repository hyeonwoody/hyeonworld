package com.toyproject.hyeonworld.api.party.domain;

import static com.toyproject.hyeonworld.api.party.infrastructure.entity.PartyDashboard.*;
import com.toyproject.hyeonworld.api.party.infrastructure.PartyDashboardRepository;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.PartyDashboard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PartyDashboardService {
  private final PartyDashboardRepository partyDashboardRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void createPartyDashboard(long partyId) {
    try {
      PartyDashboard dashboard = create(partyId);
      log.info("Attempting to save PartyDashboard for partyId: {}", partyId);
      PartyDashboard savedDashboard = partyDashboardRepository.save(dashboard);
      log.info("Successfully saved PartyDashboard with id: {} for partyId: {}", savedDashboard.getPartyId(), partyId);
    } catch (Exception e) {
      log.error("Failed to save PartyDashboard for partyId: {}", partyId, e);
      throw e;
    }
  }
}
