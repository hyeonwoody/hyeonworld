package com.toyproject.hyeonworld.api.round.event.Submission;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */

public record AnswerSubmissionEvent (
        long partyId,
        long userId,
        String answer
) implements SubmissionEvent {

    public static CustomEvent from(long partyId, RoundPlayCommand command) {
        return new AnswerSubmissionEvent(partyId, command.userId(), command.answer());
    }
}
