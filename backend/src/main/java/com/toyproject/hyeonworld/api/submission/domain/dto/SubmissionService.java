package com.toyproject.hyeonworld.api.submission.domain.dto;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCheckCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
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
  private final SubmissionRepository submissionRepository;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public SubmissionInfo hand(long roundId, SubmissionCommand command) {
    return from(command.partyId(), submissionRepository.save(createEntity(roundId, command)));
  }

  @Transactional
  public SubmissionCheckInfos checkSubmissions(long roundId) {
    return SubmissionCheckInfos.from(submissionRepository.findMostRecentByRoundId(roundId));
  }

}
