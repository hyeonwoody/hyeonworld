package com.toyproject.hyeonworld.api.session.controller.dto.req;

import com.toyproject.hyeonworld.api.session.application.dto.in.CreateLoginSessionCommand;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 4.
 */
public record SessionCreateRequest (
    String userName
) implements SessionRequest
{
  public CreateLoginSessionCommand toCommand() {
    return new CreateLoginSessionCommand(userName);
  }
}
