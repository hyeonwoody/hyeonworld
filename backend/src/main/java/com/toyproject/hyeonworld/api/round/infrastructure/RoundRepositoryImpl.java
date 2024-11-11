package com.toyproject.hyeonworld.api.round.infrastructure;

import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import com.toyproject.hyeonworld.api.round.infrastructure.jdbc.RoundJdbcTemplateRepositoryImpl;
import com.toyproject.hyeonworld.api.round.infrastructure.jpa.RoundJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class RoundRepositoryImpl implements RoundRepository{
  private final RoundJpaRepository roundJpaRepository;
  private final RoundJdbcTemplateRepositoryImpl roundJdbcTemplateRepositoryImpl;

  @Override
  public Round insert(Round round) {
    return roundJdbcTemplateRepositoryImpl.insert(round);
  }

  @Override
  public Optional<Round> findById(long id) {
    return roundJpaRepository.findById(id);
  }

  @Override
  public Round update(Round round) {
    return roundJdbcTemplateRepositoryImpl.update(round);
  }

  @Override
  public Optional<Round> findByPartyId(long partyId) {
    return roundJdbcTemplateRepositoryImpl.findByPartyId(partyId);
  }
}
