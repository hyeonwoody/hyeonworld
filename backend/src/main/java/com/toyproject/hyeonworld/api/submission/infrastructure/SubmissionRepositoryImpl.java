package com.toyproject.hyeonworld.api.submission.infrastructure;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.submission.infrastructure.jpa.SubmissionJpaRepository;
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

  @Override
  public Submission save(Submission submission) {
    return submissionJpaRepository.save(submission);
  }
}
