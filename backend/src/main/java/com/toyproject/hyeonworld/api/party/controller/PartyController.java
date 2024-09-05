package com.toyproject.hyeonworld.api.party.controller;

import static com.toyproject.hyeonworld.api.party.controller.dto.res.PartyResponse.Begin.*;

import com.toyproject.hyeonworld.api.party.controller.dto.req.PartyRequest;
import com.toyproject.hyeonworld.api.party.controller.dto.res.PartyResponse;
import com.toyproject.hyeonworld.api.party.domain.PartyService;
import com.toyproject.hyeonworld.api.user.controller.dto.req.UserRequest;
import com.toyproject.hyeonworld.api.user.controller.dto.res.UserResponse;
import com.toyproject.hyeonworld.api.user.domain.UserService;
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

  private final PartyService partyService;

  @PostMapping
  public ResponseEntity<PartyResponse.Begin> beginParty(@RequestBody PartyRequest.Begin request){
    return ResponseEntity.ok(from(partyService.begin(request.toCommand())));
  }
}
