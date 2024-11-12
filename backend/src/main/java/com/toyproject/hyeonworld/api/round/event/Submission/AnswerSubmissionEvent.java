package com.toyproject.hyeonworld.api.round.event.Submission;

import com.toyproject.hyeonworld.api.game.strategy.GameStrategy;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */

public record AnswerSubmissionEvent (
        GameStrategy gameStrategy,
        RoundPlayCommand roundPlayCommand
) implements SubmissionEvent {

    public static CustomEvent from(GameStrategy gameStrategy, RoundPlayCommand command) {
        return new AnswerSubmissionEvent(gameStrategy, command);
    }
}
