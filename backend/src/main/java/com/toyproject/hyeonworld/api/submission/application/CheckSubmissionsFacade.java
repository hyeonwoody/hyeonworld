package com.toyproject.hyeonworld.api.submission.application;

import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 8.
*/
@Facade
@RequiredArgsConstructor
public class CheckSubmissionsFacade {
  private final SubmissionService submissionService;
  private final UserService userService;


}
