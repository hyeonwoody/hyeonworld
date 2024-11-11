package com.toyproject.hyeonworld.api.submission.infrastructure;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.submission.infrastructure.jdbc.SubmissionJdbcTemplateRepository;
import com.toyproject.hyeonworld.api.submission.infrastructure.jpa.SubmissionJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Repository
@Primary
@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionRepository{
  private final SubmissionJpaRepository submissionJpaRepository;
  private final SubmissionJdbcTemplateRepository submissionJdbcTemplateRepository;

  @Override
  public Submission save(Submission submission) {
    return submissionJpaRepository.save(submission);
  }

  public List<Submission> findAllByRoundId(long roundId) {
    return submissionJpaRepository.findAllByRoundId(roundId);
  }

  @Override
  public List<Submission> findMostRecentByRoundId(long roundId) {
    return submissionJdbcTemplateRepository.findMostRecentByRoundId(roundId);
  }

  @Override
  public Submission findMostRecentByUserId(long userId) {
    return submissionJdbcTemplateRepository.findMostRecentByUserId(userId);
  }

  @Override
  public Optional<Submission> findById(long submissionId) {
    return submissionJpaRepository.findById(submissionId);
  }
}
