package com.toyproject.hyeonworld.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PartyInitDTO {
    private Integer partyType;
    private Integer persons;


    @JsonCreator
    public PartyInitDTO(@JsonProperty("partyType") Integer partyType,
                         @JsonProperty("persons") Integer persons) {
        this.partyType = partyType;
        this.persons = persons;
    }

//    public PartyInitDTO(Integer partyType, Integer persons) {
//        this.partyType = partyType;
//        this.persons = persons;
//    }
}
