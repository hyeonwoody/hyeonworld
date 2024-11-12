package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record RoundBeginResponse(
        long id
) implements RoundResponse {

    public static RoundBeginResponse from(RoundInfo roundInfo) {
        return ObjectrMapper.convert(roundInfo, RoundBeginResponse.class);
    }
}
