package com.toyproject.hyeonworld.api.submission.domain;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.submission.infrastructure.SubmissionRepository;

import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
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
  private final SubmissionRepository submissionRepository;

  public RoundSubmissionInfo retrieveById(long submissionId) {
    return RoundSubmissionInfo.from(submissionRepository.findById(submissionId)
        .orElseThrow(()-> new ServerException(ServerResponseCode.SUBMISSION_NOT_FOUND)));
  }

  public SubmissionInfo retrieveByUserId(long userId) {
    return SubmissionInfo.from(submissionRepository.findMostRecentByUserId(userId));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public SubmissionInfo hand(long roundId, SubmissionCommand command) {
    return from(command.partyId(), submissionRepository.save(createEntity(roundId, command)));
  }

  @Transactional
  public RoundSubmissionInfos check(long roundId) {
    return RoundSubmissionInfos.from(submissionRepository.findMostRecentByRoundId(roundId));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public AnswerSubmissionInfo submitAnswer(long roundId, RoundPlayCommand command) {
    return AnswerSubmissionInfo.from(submissionRepository.saveAnswer(AnswerSubmissionInfo.createEntity(roundId, command)));
  }

  public AnswerSubmissionInfos retrieveAnswerSubmissions(long roundId) {
    return AnswerSubmissionInfos.from(submissionRepository.findAnswerMostRecentByRoundId(roundId));
  }
}
