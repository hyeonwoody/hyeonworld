package com.toyproject.hyeonworld.api.party.controller;

import com.toyproject.hyeonworld.api.party.application.PartyFacade;
import com.toyproject.hyeonworld.api.party.controller.dto.req.PartyBeginRequest;
import com.toyproject.hyeonworld.api.party.controller.dto.req.PartyRequest;
import com.toyproject.hyeonworld.api.party.controller.dto.res.PartyBeginResponse;
import com.toyproject.hyeonworld.api.party.controller.dto.res.PartyTerminateResponse;
import com.toyproject.hyeonworld.api.party.domain.PartyService;
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
* @since : 24. 9. 5.
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/parties")
public class PartyController {
  private final PartyFacade partyFacade;

  @PostMapping
  public ResponseEntity<PartyBeginResponse> beginParty(@RequestBody PartyBeginRequest request){
    return ResponseEntity.ok(PartyBeginResponse.from(partyFacade.begin(request.toCommand())));
  }

  @PatchMapping("/{partyId}")
  public ResponseEntity<PartyTerminateResponse> terminateParty(
      @PathVariable long partyId
  ){
    return ResponseEntity.ok(PartyTerminateResponse.from(partyFacade.terminate(partyId)));
  }



}
