package com.toyproject.hyeonworld.api.round.domain;

import static com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo.*;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundAnswerCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.infrastructure.RoundRepository;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Service
@RequiredArgsConstructor
public class RoundService {
  private final RoundRepository roundRepository;

  public RoundInfo begin(BeginRoundCommand command) {
    return from(roundRepository.insert(create(command)));
  }

  public RoundInfo updateAnswer(RoundAnswerCommand command) {
    RoundInfo roundInfo = from(roundRepository.findById(command.id())
        .orElseThrow(()-> new ServerException(ServerResponseCode.ROUND_NOT_FOUND)));
    return from(roundRepository.update(roundInfo.entityToUpdate(command)));
  }

}
