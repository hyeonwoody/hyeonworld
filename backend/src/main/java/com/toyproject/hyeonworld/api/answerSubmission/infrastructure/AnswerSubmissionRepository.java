package com.toyproject.hyeonworld.api.answerSubmission.infrastructure;

import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public interface AnswerSubmissionRepository {

    AnswerSubmission saveAnswer(AnswerSubmission answerSubmission);

    List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId);
}
