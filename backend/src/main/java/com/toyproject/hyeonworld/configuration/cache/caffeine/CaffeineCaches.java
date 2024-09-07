package com.toyproject.hyeonworld.configuration.cache.caffeine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Component
@ConfigurationProperties(prefix = "cache.caffeine")//application.yml
//cache:
//  caffeine:
//    cache-names:
//      - cache1
//      - cache2
//      - cache3
public class CaffeineCaches {
  private List<String> cacheNames = List.of("partyDashboardInfo","roundInfo");

  public List<String> getCacheNames() {
    return cacheNames;
  }

  public void setCacheNames(List<String> cacheNames) {
    this.cacheNames = cacheNames;
  }
}