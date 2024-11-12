package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record CheckResponse (
        long userId,
        String name,
        String text,
        String number
) implements RoundResponse {


    public static List<CheckResponse> from(
            RoundSubmissionInfos roundSubmissionInfos) {
        return roundSubmissionInfos.stream()
                .map(CheckResponse::from)
                .toList();
    }

    private static CheckResponse from(RoundSubmissionInfo roundSubmissionInfo) {
        return ObjectrMapper.convert(roundSubmissionInfo, CheckResponse.class);
    }

}
