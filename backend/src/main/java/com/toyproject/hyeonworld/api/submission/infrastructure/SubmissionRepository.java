package com.toyproject.hyeonworld.api.submission.infrastructure;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import java.util.List;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/public interface SubmissionRepository {

  Submission save(Submission submission);

  List<Submission> findAllByRoundId(long roundId);

  List<Submission> findMostRecentByRoundId(long roundId);
}
