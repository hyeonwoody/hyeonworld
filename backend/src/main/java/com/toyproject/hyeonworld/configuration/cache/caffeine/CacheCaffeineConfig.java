package com.toyproject.hyeonworld.configuration.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Configuration
@RequiredArgsConstructor
public class CacheCaffeineConfig {
  private final CaffeineCaches caffeineCaches;

  @Bean
  @Primary
  public CacheManager caffeineCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCacheNames(caffeineCaches.getCacheNames());
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }

  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .maximumSize(300);
  }
}
