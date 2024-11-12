package com.toyproject.hyeonworld.api.round.domain.dto.out;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public record UserNameInfo(
        long id,
        String name
){

    public UserNameInfo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserNameInfo from(long id, String name) {
        return new UserNameInfo(id, name);
    }

}
