package com.toyproject.hyeonworld.api.answerSubmission.infrastructure;

import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.jdbc.AnswerSubmissionJdbctemplateRepository;
import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.jpa.AnswerSubmissionJpaRepository;
import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class AnswerSubmissionRepositoryImpl implements AnswerSubmissionRepository {
    private final AnswerSubmissionJdbctemplateRepository answerSubmissionJdbctemplateRepository;
    private final AnswerSubmissionJpaRepository answerSubmissionJpaRepository;

    @Override
    public AnswerSubmission saveAnswer(AnswerSubmission answerSubmission) {
        return answerSubmissionJpaRepository.save(answerSubmission);
    }

    @Override
    public List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId) {
        return answerSubmissionJdbctemplateRepository.findAnswerMostRecentByRoundId(roundId);
    }
}
