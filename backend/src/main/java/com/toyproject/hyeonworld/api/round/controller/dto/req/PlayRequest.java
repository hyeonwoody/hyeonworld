package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record PlayRequest(
        long partyId,
        long userId,
        String answer
) implements RoundRequest {

    public RoundPlayCommand toCommand() {
        return new RoundPlayCommand(partyId, userId, answer);
    }

}
