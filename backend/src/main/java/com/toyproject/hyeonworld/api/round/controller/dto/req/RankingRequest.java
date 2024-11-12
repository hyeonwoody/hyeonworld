package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundRankingCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record RankingRequest(
        long partyId,
        long roundId
)
        implements RoundRequest {

    public RoundRankingCommand toCommand() {
        return new RoundRankingCommand(partyId, roundId);
    }

}