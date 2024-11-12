package com.toyproject.hyeonworld.api.submission.domain;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;

import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.AnswerSubmissionRepository;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SubmissionInfo handFromParticipants(long roundId, SubmissionCommand command) {
        return SubmissionInfo.from(command.partyId(),
                submissionRepository.save(SubmissionInfo.toEntity(roundId, command)));
    }

    @Transactional
    public RoundSubmissionInfos check(long roundId) {
        return RoundSubmissionInfos.from(submissionRepository.findMostRecentByRoundId(roundId));
    }

    public SubmissionInfo checkConfirmSubmission(long userId) {
        return SubmissionInfo.from(submissionRepository.findMostRecentByUserId(userId));
    }

    public RoundSubmissionInfo showById(long submissionId) {
        return RoundSubmissionInfo.from(submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ServerException(ServerResponseCode.SUBMISSION_NOT_FOUND)));
    }
}
