package com.toyproject.hyeonworld.api.user.application.dto;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public record NameDto(
        long id,
        String name
){

    public NameDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static NameDto from(long id, String name) {
        return new NameDto(id, name);
    }

}
