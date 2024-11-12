package com.toyproject.hyeonworld.api.round.controller.dto.req.submit;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.RoundSubmissionCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record CheckRequest(
        long roundId
) implements RoundSubmitRequest {

    public RoundSubmissionCommand toCommand(long roundId) {
        return new RoundSubmissionCommand(roundId);
    }
}