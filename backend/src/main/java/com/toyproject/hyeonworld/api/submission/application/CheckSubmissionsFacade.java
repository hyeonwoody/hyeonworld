package com.toyproject.hyeonworld.api.submission.application;

import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCheckCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 8.
*/
@Facade
@RequiredArgsConstructor
public class CheckSubmissionsFacade {
  private final SubmissionService submissionService;
  private final UserService userService;

  @Transactional
  public SubmissionCheckInfos checkSubmissions(SubmissionCheckCommand command) {
    SubmissionCheckInfos submissionCheckInfos = submissionService.checkSubmissions(command.roundId());

    for (SubmissionCheckInfo submissionCheckInfo : submissionCheckInfos){
      String userName = userService.getNameById(submissionCheckInfo.getUserId());
      submissionCheckInfo.complete(userName);
    }
    return submissionCheckInfos;
  }
}
