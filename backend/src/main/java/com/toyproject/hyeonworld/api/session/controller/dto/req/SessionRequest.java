package com.toyproject.hyeonworld.api.session.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public record SessionRequest(
    String userName
) {
  public CreateLoginSessionCommand toCommand() {
    return new CreateLoginSessionCommand(userName);
  }
}
