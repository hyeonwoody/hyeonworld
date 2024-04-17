package com.toyproject.hyeonworld.DTO.Member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class MemberAnswer extends MemberDTO{
    private final Long memberId;
    private final Integer answer;


    @JsonCreator
    public MemberAnswer(@JsonProperty("memberId") Long memberId,
                        @JsonProperty("answer") Integer answer
    ) {
        this.memberId = memberId;
        this.answer = answer;
    }
}
