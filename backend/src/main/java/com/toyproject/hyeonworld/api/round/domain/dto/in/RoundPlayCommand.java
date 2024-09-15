package com.toyproject.hyeonworld.api.round.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public record RoundPlayCommand(
    long partyId,
    long userId,
    String answer
){

}
