package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.RankStage;
import com.toyproject.hyeonworld.api.user.application.dto.NameScoreDto;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record RankingResponse(
        List<Participant> rank
) implements RoundResponse {

    public RankingResponse(List<Participant> rank) {
        this.rank = new ArrayList<>(rank);
    }

    record Participant(
            String name,
            Long score
    ) {

        public static Participant from(String userName, long score) {
            if (userName == null || userName.trim().isEmpty()) {
                throw new IllegalArgumentException("User name cannot be null or empty");
            }
            return new Participant(userName, score);
        }
    }

    public static RankingResponse from(RankStage rankStage) {
        return new RankingResponse(convertParticipant(rankStage.participants()));
    }

    private static List<Participant> convertParticipant(PriorityQueue<NameScoreDto> pq) {

        return Stream.generate(pq::poll)
                .limit(pq.size())
                .map(participant -> Participant.from(participant.name(),
                        participant.score()))
                .toList();
    }
}
