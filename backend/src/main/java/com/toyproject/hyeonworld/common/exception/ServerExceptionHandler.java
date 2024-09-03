package com.toyproject.hyeonworld.common.exception;

import com.toyproject.hyeonworld.common.exception.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 8. 29.
 */
@RestControllerAdvice
public class ServerExceptionHandler {
  @ExceptionHandler(value ={ServerException.class})
  protected ResponseEntity<ExceptionResponse> handleCustomException (ServerException e, HttpServletRequest request){
    return ExceptionResponse.toResponseEntity(e.getCode(), e.getMessage());
  }
}
