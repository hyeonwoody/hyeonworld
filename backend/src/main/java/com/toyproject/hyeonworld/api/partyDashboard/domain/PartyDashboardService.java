package com.toyproject.hyeonworld.api.partyDashboard.domain;

import static com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out.PartyDashboardInfo.from;
import static com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard.*;

import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.in.ChangeDashboardCommand;
import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.PartyDashboardRepository;
import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard;
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

  public PartyDashboardInfo changeDashboard(ChangeDashboardCommand command) {
    return from(partyDashboardRepository.save(create(command)));
  }
}
