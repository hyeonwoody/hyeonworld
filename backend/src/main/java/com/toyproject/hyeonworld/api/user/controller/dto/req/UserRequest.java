package com.toyproject.hyeonworld.api.user.controller.dto.req;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UserRequest (){
  public record create(
      String name,
      Byte relationType,
      Byte relation
  ){
    public CreateUserCommand toCommand() {
      return new CreateUserCommand(name, relationType, relation);
    }
  }
}
