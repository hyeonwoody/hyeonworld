package com.toyproject.hyeonworld.DTO.Member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberCreateDTO {

    private String name;
    private Byte partyType;
    private Byte relation;

}
