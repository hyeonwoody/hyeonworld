package com.toyproject.hyeonworld.api.session.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;
import com.toyproject.hyeonworld.api.session.application.dto.in.DeleteLoginSessionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public record SessionRequest(
    String userName
) {
  public record create(
      String userName
  ){
    public CreateLoginSessionCommand toCommand() {
      return new CreateLoginSessionCommand(userName);
    }
  }
  public record delete(
      long userId
  ){
    public DeleteLoginSessionCommand toCommand() {
      return new DeleteLoginSessionCommand(userId);
    }
  }
}
