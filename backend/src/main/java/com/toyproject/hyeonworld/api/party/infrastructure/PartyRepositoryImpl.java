package com.toyproject.hyeonworld.api.party.infrastructure;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.party.infrastructure.jdbc.PartyJdbcTemplateRepository;
import com.toyproject.hyeonworld.api.party.infrastructure.jpa.PartyJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Repository
@RequiredArgsConstructor
public class PartyRepositoryImpl implements PartyRepository{
  private final PartyJpaRepository partyJpaRepository;
  private final PartyJdbcTemplateRepository partyJdbcTemplateRepository;

  @Override
  public Party save(Party party) {
    return partyJdbcTemplateRepository.save(party);
  }

  @Override
  public Optional<Party> findById(long partyId) {
    return partyJpaRepository.findById(partyId);
  }
}
