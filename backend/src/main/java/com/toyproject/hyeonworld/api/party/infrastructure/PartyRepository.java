package com.toyproject.hyeonworld.api.party.infrastructure;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyRepository {

  Party save(Party party);

  Optional<Party> findById(long partyId);

    long findByRelationType(byte relationType);
}
