package com.toyproject.hyeonworld.api.round.infrastructure;

import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import com.toyproject.hyeonworld.api.round.infrastructure.jdbc.RoundjdbcTemplateRepository;
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
  private final RoundjdbcTemplateRepository roundJdbcTemplateRepository;

  @Override
  public Round insert(Round round) {
    return roundJdbcTemplateRepository.insert(round);
  }

  @Override
  public Optional<Round> findById(long id) {
    return roundJpaRepository.findById(id);
  }

  @Override
  public Round update(Round round) {
    return roundJdbcTemplateRepository.update(round);
  }

  @Override
  public Optional<Round> findByPartyId(long partyId) {
    return roundJdbcTemplateRepository.findByPartyId(partyId);
  }
}
