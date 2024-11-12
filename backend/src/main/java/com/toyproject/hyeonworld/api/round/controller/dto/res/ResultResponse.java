package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultStage;
import com.toyproject.hyeonworld.api.user.application.dto.NameDtos;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record ResultResponse(
        String answer,
        List<Winner> winners
) implements RoundResponse {

    record Winner(
            Long id,
            String name
    ) {

        public static Winner from(Long userId, String userName) {
            if (userId == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }
            if (userName == null || userName.trim().isEmpty()) {
                throw new IllegalArgumentException("User name cannot be null or empty");
            }
            return new Winner(userId, userName);
        }
    }

    public static ResultResponse from(ResultStage resultStage) {
        return new ResultResponse(resultStage.answer(), convertWinners(resultStage.nameDtos()));
    }

    private static List<Winner> convertWinners(NameDtos winners) {
        if (winners == null) {
            throw new IllegalArgumentException("Winner IDs and names must be non-null and have the same size");
        }
        return winners.nameDtos().stream()
                .map(winner -> Winner.from(winner.id(), winner.name()))
                .toList();
    }
}
