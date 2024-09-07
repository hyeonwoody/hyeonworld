package com.toyproject.hyeonworld.api.round.controller;

import static com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Begin.*;
import static com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Answer.*;

import com.toyproject.hyeonworld.api.round.application.SubmissionFacade;
import com.toyproject.hyeonworld.api.round.controller.dto.req.RoundRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Answer;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Begin;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionRequest;
import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/rounds")
public class RoundController {
  private final RoundService roundService;
  private final SubmissionFacade submissionFacade;
  @PostMapping
  public ResponseEntity<RoundResponse.Begin> beginRound(@RequestBody RoundRequest.Begin request){
    return ResponseEntity.ok(Begin.from(roundService.begin(request.toCommand())));
  }

  @PatchMapping("/{roundId}")
  public ResponseEntity<RoundResponse.Answer> updateAnswer(
      @PathVariable long roundId,
      @RequestBody RoundRequest.Answer request){
    return ResponseEntity.ok(Answer.from(roundService.updateAnswer(request.toCommand(roundId))));
  }

  @PostMapping("/submits")
  public ResponseEntity<SubmissionResponse> handSubmission(@RequestBody SubmissionRequest request){
    return ResponseEntity.ok(SubmissionResponse.from(submissionFacade.handSubmission(request.toCommand())));
  }

}
