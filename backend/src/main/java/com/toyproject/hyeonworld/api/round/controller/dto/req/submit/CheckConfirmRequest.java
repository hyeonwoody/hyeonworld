package com.toyproject.hyeonworld.api.round.controller.dto.req.submit;

import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record CheckConfirmRequest(
        long gameId,
        long userId,
        String text,
        long number
)implements RoundSubmitRequest {

    public SubmissionCheckConfirmCommand toCommand(long roundId) {
        return new SubmissionCheckConfirmCommand(roundId, gameId, userId, text, number);
    }
}
