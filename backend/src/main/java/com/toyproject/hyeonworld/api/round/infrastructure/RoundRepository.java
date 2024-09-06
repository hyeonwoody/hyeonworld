package com.toyproject.hyeonworld.api.round.infrastructure;

import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public interface RoundRepository {

  Round insert(Round round);

  Optional<Round> findById(long id);

  Round update(Round round);

  Optional<Round> findByPartyId(long partyId);
}
