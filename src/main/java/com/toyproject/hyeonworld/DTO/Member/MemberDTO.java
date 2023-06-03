package com.toyproject.hyeonworld.DTO.Member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberDTO {

    private String memberName;


    public MemberDTO(){

    }

    @JsonCreator
    public MemberDTO(@JsonProperty("memberName") String memberName
    ) {
        this.memberName = memberName;
    }



}
