package com.toyproject.hyeonworld.api.round.event.Submission;

import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public record PrimarySubmissionEvent (long roundId, SubmissionCommand command) implements SubmissionEvent {

    public static CustomEvent from(long id, SubmissionCommand command) {
        return new PrimarySubmissionEvent(id, command);
    }
}