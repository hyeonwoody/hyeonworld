package com.toyproject.hyeonworld.common.exception.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 8. 29.
 */
@Getter
@Builder
public class ExceptionResponse {
  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int statusCode;
  private final String statusCodeName;
  private final String code;
  private final String message;

  public static ResponseEntity<ExceptionResponse> toResponseEntity(
      ServerResponseCode code, String runtimeValue
  ) {
    return ResponseEntity
        .status(code.getHttpStatus())
        .body(ExceptionResponse.builder()
            .statusCode(code.getHttpStatus().value())
            .statusCodeName(code.getHttpStatus().name())
            .code(code.name())
            .message(code.getMessage())
            .build()
        );
  }

  public static ExceptionResponse of(ServerResponseCode code, String runtimeValue) {
    return ExceptionResponse.builder()
        .statusCode(code.getHttpStatus().value())
        .statusCodeName(code.getHttpStatus().name())
        .code(code.name())
        .message(code.getMessage())
        .build();
  }
}
