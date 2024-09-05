package com.toyproject.hyeonworld.api.partyDashboard.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record ChangeDashboardCommand (
    Long partyId,
    Long gameId,
    Byte gameStage
) {
}
