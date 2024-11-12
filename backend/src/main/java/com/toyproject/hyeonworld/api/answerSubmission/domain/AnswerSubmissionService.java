package com.toyproject.hyeonworld.api.answerSubmission.domain;

import com.toyproject.hyeonworld.api.answerSubmission.domain.out.AnswerSubmissionInfo;
import com.toyproject.hyeonworld.api.answerSubmission.domain.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.AnswerSubmissionRepository;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
@Service
@RequiredArgsConstructor
public class AnswerSubmissionService {
    private final AnswerSubmissionRepository answerSubmissionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnswerSubmissionInfo submitAnswer(long roundId, long userId, String answer) {
        return AnswerSubmissionInfo.from(
                answerSubmissionRepository.saveAnswer(AnswerSubmissionInfo.toEntity(roundId, userId, answer)));
    }

    public AnswerSubmissionInfos retrieveAnswerSubmissions(long roundId) {
        return AnswerSubmissionInfos.from(answerSubmissionRepository.findAnswerMostRecentByRoundId(roundId));
    }
}
