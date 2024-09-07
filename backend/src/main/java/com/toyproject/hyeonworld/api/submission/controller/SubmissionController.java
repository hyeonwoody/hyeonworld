package com.toyproject.hyeonworld.api.submission.controller;

import static com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionCheckResponse.*;
import static com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse.*;


import com.toyproject.hyeonworld.api.submission.application.CheckSubmissionsFacade;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionCheckRequest;
import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionCheckResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final CheckSubmissionsFacade submissionFacade;

  @GetMapping
  public ResponseEntity<List<SubmissionCheckResponse>> checkSubmissions(@RequestBody SubmissionCheckRequest request){
    return ResponseEntity.ok(from(submissionFacade.checkSubmissions(request.toCommand())));
  } //check stage
}
