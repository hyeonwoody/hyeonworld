package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record RoundBeginRequest(long partyId,
                    long gameId

) implements RoundRequest {

    public BeginRoundCommand toCommand() {
        return new BeginRoundCommand(partyId, gameId);
    }
}
