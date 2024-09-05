package com.toyproject.hyeonworld.common.exception.dto;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 8. 29.
 */
@Getter
@RequiredArgsConstructor
public enum ServerResponseCode {
  USER_NOT_FOUND(NOT_FOUND, "The user is not in DB");


  private final HttpStatus httpStatus;
  private final String message;
}