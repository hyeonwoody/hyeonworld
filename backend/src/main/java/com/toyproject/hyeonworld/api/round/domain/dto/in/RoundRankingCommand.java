package com.toyproject.hyeonworld.api.round.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record RoundRankingCommand (
    long partyId,
    long roundId
) {
}
