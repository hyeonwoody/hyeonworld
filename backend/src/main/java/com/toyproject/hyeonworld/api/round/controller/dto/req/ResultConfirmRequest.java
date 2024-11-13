package com.toyproject.hyeonworld.api.round.controller.dto.req;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand.Participant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record ResultConfirmRequest(
        List<Winner> winners
) {
    record Winner(
            long id,
            long score
    ) {


    }
    public RoundResultConfirmCommand toCommand(long roundId) {
        List<Participant> convertedParticipants = this.winners.stream()
                .map(winner -> new Participant(winner.id(), winner.score()))
                .collect(Collectors.toList());

        return new RoundResultConfirmCommand(roundId, convertedParticipants);
    }
}
