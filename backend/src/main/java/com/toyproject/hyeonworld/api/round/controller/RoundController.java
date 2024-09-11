package com.toyproject.hyeonworld.api.round.controller;

import static com.toyproject.hyeonworld.api.round.controller.dto.res.RoundSubmissionResponse.Basic.from;

import com.toyproject.hyeonworld.api.round.application.SubmissionFacade;
import com.toyproject.hyeonworld.api.round.controller.dto.req.RoundRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundResponse.Begin;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.controller.dto.req.SubmissionCheckRequest;
import com.toyproject.hyeonworld.api.submission.controller.dto.req.SubmissionRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundSubmissionResponse;
import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

//  @PatchMapping("/{roundId}")
//  public ResponseEntity<RoundResponse.Answer> updateAnswer(
//      @PathVariable long roundId,
//      @RequestBody RoundRequest.Answer request){
//    return ResponseEntity.ok(Answer.from(roundService.updateAnswer(request.toCommand(roundId))));
//  }

  /*
  참가자는 partyId 정보만 알고
  사회자만 partyId, roundId 정보를 관리합니다.
   */
  @PostMapping("/submits")
  public ResponseEntity<SubmissionResponse.Basic> submitSubmission(@RequestBody SubmissionRequest.Basic request){
    return ResponseEntity.ok(SubmissionResponse.Basic.from(submissionFacade.submitSubmission(request.toCommand())));
  }

  @GetMapping("/{roundId}/checks")
  public ResponseEntity<List<RoundSubmissionResponse.Basic>> checkSubmissions(
      @PathVariable long roundId,
      @RequestBody SubmissionCheckRequest.Basic request){
    return ResponseEntity.ok(RoundSubmissionResponse.Basic.from(submissionFacade.check(request.toCommand(roundId))));
  } //check stage

  @PatchMapping("/{roundId}/check-confirm")
  public ResponseEntity<RoundSubmissionResponse.Confirm> checkSubmissionConfirm(
      @PathVariable long roundId,
      @RequestBody SubmissionCheckRequest.Confirm request){
    return ResponseEntity.ok(RoundSubmissionResponse.Confirm.from(submissionFacade.checkConfirm(request.toCommand(roundId))));
  }

//  @GetMapping("/{roundId}/shows")
//  public ResponseEntity<RoundSubmissionResponse.Show> show (
//      @PathVariable long roundId,
//      @RequestBody SubmissionCheckRequest.Show request){
//    return ResponseEntity.ok(RoundSubmissionResponse.Confirm.from(submissionFacade.show(request.toCommand(roundId))));
//  }
}
