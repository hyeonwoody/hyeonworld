package com.toyproject.hyeonworld.api.session.controller;

import com.toyproject.hyeonworld.api.session.controller.dto.req.SessionCreateRequest;
import com.toyproject.hyeonworld.api.session.controller.dto.req.SessionDeleteRequest;
import com.toyproject.hyeonworld.api.session.controller.dto.req.SessionGameRequest;

import com.toyproject.hyeonworld.api.session.application.SessionFacade;
import com.toyproject.hyeonworld.api.session.controller.dto.res.SessionBasicResponse;
import com.toyproject.hyeonworld.api.session.controller.dto.res.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth/session")
public class SessionController {
  private final SessionFacade sessionFacade;

  @PostMapping
  public ResponseEntity<SessionBasicResponse> processLogin(@RequestBody SessionCreateRequest request){
    return ResponseEntity.ok(SessionBasicResponse.from(sessionFacade.createLoginSession(request.toCommand())));
  }

  @PostMapping("/game")
  public ResponseEntity<SessionBasicResponse> processEnterGame(@RequestBody SessionGameRequest request){
    return ResponseEntity.ok(SessionBasicResponse.from(sessionFacade.enterGame(request.toCommand())));
  }

  @DeleteMapping("/game")
  public ResponseEntity<SessionBasicResponse> processExitGame(@RequestBody SessionGameRequest request){
    return ResponseEntity.ok(SessionBasicResponse.from(sessionFacade.exitGame(request.toCommand())));
  }

  @DeleteMapping
  public ResponseEntity<SessionBasicResponse> processLogout(@RequestBody SessionDeleteRequest request){
    return ResponseEntity.ok(SessionBasicResponse.from(sessionFacade.deleteLoginSession(request.toCommand())));
  }


}
