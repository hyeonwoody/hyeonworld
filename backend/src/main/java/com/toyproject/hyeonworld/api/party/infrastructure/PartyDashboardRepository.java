package com.toyproject.hyeonworld.api.party.infrastructure;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.PartyDashboard;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyDashboardRepository {

  PartyDashboard save(PartyDashboard partyDashboard);

  PartyDashboard saveAndFlush(PartyDashboard dashboard);
}
