package com.toyproject.hyeonworld.common.exception;

import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 8. 29.
 */
@Getter
public class ServerException extends RuntimeException{
  private final ServerResponseCode code;

  public ServerException(ServerResponseCode code) {
    this(code, "runtimeMessage is not available");
  }

  public ServerException(ServerResponseCode code, String runtimeMessage){
    super(code.getMessage() + ": " + runtimeMessage);
    this.code = code;
  }
}
