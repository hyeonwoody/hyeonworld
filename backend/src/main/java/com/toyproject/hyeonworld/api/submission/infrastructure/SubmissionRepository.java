package com.toyproject.hyeonworld.api.submission.infrastructure;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/public interface SubmissionRepository {

  Submission save(Submission submission);
}
