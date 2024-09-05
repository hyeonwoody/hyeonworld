package com.toyproject.hyeonworld.api.user.controller.dto.req;

import com.toyproject.hyeonworld.api.user.controller.dto.req.UserRequest.create;
import com.toyproject.hyeonworld.api.user.controller.dto.req.UserRequest.update;
import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.UpdateUserCommand;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public sealed interface UserRequest permits create, update, UserWaitingListRequest {
  record create (
      String name,
      Byte relationType,
      Byte relation
  ) implements UserRequest{
    public CreateUserCommand toCommand() {
      return new CreateUserCommand(name, relationType, relation);
    }
  }

  record update (
      String name,
      Byte relationType,
      Byte relation
  ) implements UserRequest{
    public UpdateUserCommand toCommand(long userId) {
      return new UpdateUserCommand(userId, Optional.ofNullable(name),
          Optional.ofNullable(relationType), Optional.ofNullable(relation));
    }
  }
}
