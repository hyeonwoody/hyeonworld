package com.toyproject.hyeonworld.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 8. 27.
 */
public class ObjectrMapper {
  private static final ObjectMapper objectMapper;

  static{
    objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static <T, U> U convert(T from, Class<U> to) {
    try {
      String json = objectMapper.writeValueAsString(from);
      return objectMapper.readValue(json, to);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Object mapping failed", e);
    }
  }

  public static <T, U> List<U> convert(List<T> from, Class<U> to) {
    return from.stream()
        .map(each -> convert(each, to))
        .collect(Collectors.toList());
  }
}
