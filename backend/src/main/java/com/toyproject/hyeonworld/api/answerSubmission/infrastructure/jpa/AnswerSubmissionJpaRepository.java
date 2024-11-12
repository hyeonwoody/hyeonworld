package com.toyproject.hyeonworld.api.answerSubmission.infrastructure.jpa;

import com.toyproject.hyeonworld.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface AnswerSubmissionJpaRepository extends JpaRepository<AnswerSubmission, Long> {

}
