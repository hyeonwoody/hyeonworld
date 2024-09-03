package com.toyproject.hyeonworld.api.user.domain.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record CreateUserCommand (
    String name,
    byte relationType,
    byte relation
){

}
