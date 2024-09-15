package com.toyproject.hyeonworld.api.submission.infrastructure;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.AnswerSubmission;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.submission.infrastructure.jdbc.SubmissionJdbcTemplateRepository;
import com.toyproject.hyeonworld.api.submission.infrastructure.jpa.AnswerSubmissionJpaRepository;
import com.toyproject.hyeonworld.api.submission.infrastructure.jpa.SubmissionJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Repository
@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionRepository{
  private final SubmissionJpaRepository submissionJpaRepository;
  private final SubmissionJdbcTemplateRepository submissionJdbcTemplateRepository;

  private final AnswerSubmissionJpaRepository answerSubmissionJpaRepository;

  @Override
  public Submission save(Submission submission) {
    return submissionJpaRepository.save(submission);
  }

  @Override
  public AnswerSubmission saveAnswer(AnswerSubmission answerSubmission) {
    return answerSubmissionJpaRepository.save(answerSubmission);
  }

  @Override
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
