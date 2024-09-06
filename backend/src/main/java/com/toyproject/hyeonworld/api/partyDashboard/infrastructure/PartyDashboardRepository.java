package com.toyproject.hyeonworld.api.partyDashboard.infrastructure;

import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.entity.PartyDashboard;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyDashboardRepository {

  PartyDashboard save(PartyDashboard partyDashboard);

  PartyDashboard saveAndFlush(PartyDashboard dashboard);

  Optional<PartyDashboard> findById(long partyId);
}
