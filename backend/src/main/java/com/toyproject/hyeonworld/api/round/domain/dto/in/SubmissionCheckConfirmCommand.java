package com.toyproject.hyeonworld.api.round.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public record SubmissionCheckConfirmCommand(
    long roundId,
    long gameId,
    long userId,
    String text,
    long number
) {

}
