package com.toyproject.hyeonworld.api.round.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public record BeginRoundCommand (
    long partyId,
    long gameId
){

}
