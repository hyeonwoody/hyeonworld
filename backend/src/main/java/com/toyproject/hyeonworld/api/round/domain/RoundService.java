package com.toyproject.hyeonworld.api.round.domain;

import static com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo.*;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.infrastructure.RoundRepository;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Service
@RequiredArgsConstructor
public class RoundService {
  private final RoundRepository roundRepository;

  @CacheEvict(cacheNames = "roundInfo", key = "#command.partyId")
  public RoundInfo begin(BeginRoundCommand command) {
    return from(roundRepository.insert(create(command)));
  }

  @Cacheable(cacheNames = "roundInfo", cacheManager = "caffeineCacheManager")
  public RoundInfo retrieveCurrentRound(long partyId) {
    return from(roundRepository.findByPartyId(partyId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
  }

  public RoundInfo updateAnswerInternal(long roundId, Object answer) {
    RoundInfo roundInfo = from(roundRepository.findById(roundId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
    return from(roundRepository.update(roundInfo.entityToUpdateAnswer(answer)));
  }

  public RoundInfo updateAnswer(long roundId, long answer) {
    return updateAnswerInternal(roundId, answer);
  }
  public RoundInfo updateAnswer(long roundId, String answer) {
    return updateAnswerInternal(roundId, answer);
  }

  @Cacheable(cacheNames = "roundGame", key = "#roundId")
  public long retrieveCurrentGame(long roundId) {
    return getGameIdFrom(roundRepository.findById(roundId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
  }

  @Cacheable(cacheNames = "roundAnswer", key = "#roundId")
  public String retrieveAnswer(long roundId) {
    return getAnswerFrom(roundRepository.findById(roundId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
  }

  public long retrievePartyId(long roundId) {
    return getPartyIdFrom(roundRepository.findById(roundId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
  }

}
