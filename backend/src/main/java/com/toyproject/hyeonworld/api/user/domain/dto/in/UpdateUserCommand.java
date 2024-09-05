package com.toyproject.hyeonworld.api.user.domain.dto.in;

import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UpdateUserCommand (
    long userId,
    Optional<String> name,
    Optional<Byte> relationType,
    Optional<Byte> relation
){

}
