package com.toyproject.hyeonworld.DTO.Member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toyproject.hyeonworld.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private String name;
    private Long id;
    private Byte partyType;
    private Byte relation;
    public MemberDTO() {

    }
    @JsonCreator
    public MemberDTO(@JsonProperty("memberName") String memberName
    ) {
        this.name = memberName;
    }

    public MemberDTO(long id, String name, byte partyType, byte relation) {
        this.id = id;
        this.name = name;
        this.partyType = partyType;
        this.relation = relation;
    }

    public MemberDTO(Member member) {

        this.id = member.getId();
        this.name = member.getName();
        this.partyType = member.getPartyType();
        this.relation = member.getRelation();
    }

    public String getName() {
        return this.name;
    }
}
