package com.toyproject.hyeonworld.api.round.controller;

import com.toyproject.hyeonworld.api.round.application.RoundScoreFacade;
import com.toyproject.hyeonworld.api.round.application.RoundSubmissionFacade;
import com.toyproject.hyeonworld.api.round.controller.dto.req.submit.CheckConfirmRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.submit.CheckRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.PlayRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.RankingRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.ResultConfirmRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.RoundBeginRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.req.submit.SubmissionRequest;
import com.toyproject.hyeonworld.api.round.controller.dto.res.submit.CheckConfirmResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.submit.CheckResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.PlayResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RankingResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.ResultConfirmResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.ResultResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.RoundBeginResponse;
import com.toyproject.hyeonworld.api.round.controller.dto.res.ShowResponse;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.controller.dto.res.submit.SubmissionResponse;
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
  private final RoundSubmissionFacade roundSubmissionFacade;
  private final RoundScoreFacade roundScoreFacade;

  @PostMapping
  public ResponseEntity<RoundBeginResponse> beginRound(@RequestBody RoundBeginRequest request){
    return ResponseEntity.ok(RoundBeginResponse.from(roundService.begin(request.toCommand())));
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
  public ResponseEntity<SubmissionResponse> submitSubmission(@RequestBody SubmissionRequest request){
    return ResponseEntity.ok(SubmissionResponse.from(roundSubmissionFacade.submitSubmission(request.toCommand())));
  }

  @GetMapping("/{roundId}/checks")
  public ResponseEntity<List<CheckResponse>> checkSubmissions(
      @PathVariable long roundId,
      @RequestBody CheckRequest request){
    return ResponseEntity.ok(CheckResponse.from(roundSubmissionFacade.check(request.toCommand(roundId))));
  } //check stage

  @PatchMapping("/{roundId}/check-confirm")
  public ResponseEntity<CheckConfirmResponse> checkSubmissionConfirm(
      @PathVariable long roundId,
      @RequestBody CheckConfirmRequest request){
    return ResponseEntity.ok(CheckConfirmResponse.from(roundSubmissionFacade.checkConfirm(request.toCommand(roundId))));
  }

  @GetMapping("/{roundId}/shows")
  public ResponseEntity<ShowResponse> show (
      @PathVariable long roundId){
    return ResponseEntity.ok(ShowResponse.from(roundSubmissionFacade.show(roundId)));
  }

  @PostMapping("/plays")
  public ResponseEntity<PlayResponse> play (
      @RequestBody PlayRequest request
  ){
    return ResponseEntity.ok(PlayResponse.from(roundSubmissionFacade.play(request.toCommand())));
  }

  @GetMapping("/{roundId}/results")
  public ResponseEntity<ResultResponse> result (
      @PathVariable long roundId
  ){
    return ResponseEntity.ok(ResultResponse.from(roundScoreFacade.result(roundId)));
  }

  @PostMapping("/{roundId}/result-score")
  public ResponseEntity<ResultConfirmResponse> resultScore (
      @PathVariable long roundId,
      @RequestBody ResultConfirmRequest request
  ){
    return ResponseEntity.ok(ResultConfirmResponse.from(roundScoreFacade.resultScoreConfirm(request.toCommand(roundId))));
  }

  @GetMapping("/rankings")
  public ResponseEntity<RankingResponse> ranking (
      @RequestBody RankingRequest request
  ){
    return ResponseEntity.ok(RankingResponse.from(roundScoreFacade.ranking(request.toCommand())));
  }

}
