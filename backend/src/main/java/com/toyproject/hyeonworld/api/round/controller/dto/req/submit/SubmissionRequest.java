package com.toyproject.hyeonworld.api.round.controller.dto.req.submit;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record SubmissionRequest (long partyId,
                                 long userId,
                                 String text,
                                 long number

) implements RoundSubmitRequest {
    public SubmissionCommand toCommand() {
        return new SubmissionCommand(partyId, userId, text, number);
    }
}
