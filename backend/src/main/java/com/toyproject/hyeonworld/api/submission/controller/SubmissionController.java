package com.toyproject.hyeonworld.api.submission.controller;

import com.toyproject.hyeonworld.api.round.application.SubmissionFacade;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionRequest;
import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/submissions")
public class SubmissionController {
  private final SubmissionFacade submissionFacade;


}
