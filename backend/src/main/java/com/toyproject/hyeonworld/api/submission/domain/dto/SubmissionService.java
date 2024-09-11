package com.toyproject.hyeonworld.api.submission.domain.dto;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.game.strategy.GameStrategy;
import com.toyproject.hyeonworld.api.game.strategy.GameStrategyFactory;
import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.submission.infrastructure.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Service
@RequiredArgsConstructor
public class SubmissionService {
  private final GameStrategyFactory gameStrategyFactory;
  private final SubmissionRepository submissionRepository;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public SubmissionInfo hand(long roundId, SubmissionCommand command) {
    return from(command.partyId(), submissionRepository.save(createEntity(roundId, command)));
  }

  @Transactional
  public RoundSubmissionInfos check(long roundId) {
    return RoundSubmissionInfos.from(submissionRepository.findMostRecentByRoundId(roundId));
  }

  public StringOrLong<?> checkConfirm(SubmissionCheckConfirmCommand command) {
    GameStrategy gameStrategy = gameStrategyFactory.getStrategy(command.gameId());
    return gameStrategy.checkConfirm(command);
  }
}
