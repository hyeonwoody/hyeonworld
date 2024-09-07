package com.toyproject.hyeonworld.configuration.cache;

import com.toyproject.hyeonworld.configuration.cache.caffeine.CacheCaffeineConfig;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Configuration
@EnableCaching
@Import({CacheCaffeineConfig.class})
@RequiredArgsConstructor
public class CacheConfig {
  private final CacheManager caffeineCacheManager;
  @Bean
  public CacheManager cacheManager() {
    return new CompositeCacheManager(caffeineCacheManager);
  }
}
