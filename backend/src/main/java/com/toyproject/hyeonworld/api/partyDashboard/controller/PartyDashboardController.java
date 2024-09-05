package com.toyproject.hyeonworld.api.partyDashboard.controller;

import static com.toyproject.hyeonworld.api.partyDashboard.controller.dto.res.PartyDashboardResponse.*;

import com.toyproject.hyeonworld.api.partyDashboard.controller.dto.req.PartyDashboardRequest.PartyDashboardRequest;
import com.toyproject.hyeonworld.api.partyDashboard.controller.dto.res.PartyDashboardResponse;
import com.toyproject.hyeonworld.api.partyDashboard.domain.PartyDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class PartyDashboardController {
  private final PartyDashboardService partyDashboardService;

  @PatchMapping("/{partyId}/dashboard")
  public ResponseEntity<PartyDashboardResponse> changeDashboard(
      @PathVariable long partyId,
      @RequestBody PartyDashboardRequest request){
    return ResponseEntity.ok(from(partyDashboardService.changeDashboard(request.toCommand(partyId))));
  }
}
