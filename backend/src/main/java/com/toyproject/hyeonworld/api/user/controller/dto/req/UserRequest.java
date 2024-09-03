package com.toyproject.hyeonworld.api.user.controller.dto.req;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.EditUserCommand;
import java.util.Optional;

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

  public record edit (
      String name,
      Byte relationType,
      Byte relation
  ){
    public EditUserCommand toCommand(long userId) {
      return new EditUserCommand(userId, Optional.ofNullable(name),
          Optional.ofNullable(relationType), Optional.ofNullable(relation));
    }
  }
}
