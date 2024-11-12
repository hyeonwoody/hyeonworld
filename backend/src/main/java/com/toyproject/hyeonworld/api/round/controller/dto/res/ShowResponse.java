package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.ShowStage;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record ShowResponse(
        String content
) implements RoundResponse {
    public static ShowResponse from(ShowStage showStage) {
        return ObjectrMapper.convert(showStage, ShowResponse.class);
    }
}
