package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.PlayInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record PlayResponse(
        long userId
) implements RoundResponse {

    public static PlayResponse from(PlayInfo playInfo) {
        return ObjectrMapper.convert(playInfo, PlayResponse.class);
    }
}
