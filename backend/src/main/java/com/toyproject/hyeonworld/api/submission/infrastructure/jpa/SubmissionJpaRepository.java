package com.toyproject.hyeonworld.api.submission.infrastructure.jpa;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public interface SubmissionJpaRepository extends JpaRepository<Submission, Long> {

  List<Submission> findAllByRoundId(long roundId);
}
