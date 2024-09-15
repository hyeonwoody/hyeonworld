package com.toyproject.hyeonworld.api.game.strategy.dto;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public class StringOrLong<T> {
  private final T value;

  private StringOrLong(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public static StringOrLong<String> ofText(String value) {
    return new StringOrLong<>(value);
  }

  public static StringOrLong<Long> ofNumber(long value) {
    return new StringOrLong<>(value);
  }

  public boolean isString() {
    return value instanceof String;
  }

  public boolean isLong() {
    return value instanceof Long;
  }
}