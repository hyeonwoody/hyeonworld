package com.toyproject.hyeonworld.api.session.controller;

import com.toyproject.hyeonworld.api.session.controller.dto.req.SessionRequest;
import com.toyproject.hyeonworld.api.session.application.SessionFacade;
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
  public ResponseEntity<SessionResponse> processLogin(@RequestBody SessionRequest.create request){
    return ResponseEntity.ok(SessionResponse.from(sessionFacade.createLoginSession(request.toCommand())));
  }
  @DeleteMapping
  public ResponseEntity<SessionResponse> processLogout(@RequestBody SessionRequest.delete request){
    return ResponseEntity.ok(SessionResponse.from(sessionFacade.deleteLoginSession(request.toCommand())));
  }
}
