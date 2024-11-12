package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 13.
 */
public record ResultConfirmResponse(

) implements RoundResponse {


    public static ResultConfirmResponse from(ScoreInfo scoreInfo) {
        return new ResultConfirmResponse();
    }
}