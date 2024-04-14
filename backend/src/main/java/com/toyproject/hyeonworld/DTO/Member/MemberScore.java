package com.toyproject.hyeonworld.DTO.Member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberScore extends MemberDTO{

    @JsonProperty ("totalScore")
    private long totalScore;

    public MemberScore (String memberName,
                        Long totalScore){
        super(memberName);
        this.totalScore = totalScore;
    }
}
