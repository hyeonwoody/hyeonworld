package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record CheckConfirmResponse(
        long partyId,
        long roundId
) implements RoundResponse {
    public static CheckConfirmResponse from(RoundSubmissionInfo roundSubmissionInfo) {
        return ObjectrMapper.convert(roundSubmissionInfo, CheckConfirmResponse.class);
    }
}
