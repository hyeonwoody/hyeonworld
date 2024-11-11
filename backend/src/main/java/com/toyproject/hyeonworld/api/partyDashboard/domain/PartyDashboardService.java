package com.toyproject.hyeonworld.api.partyDashboard.domain;

import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.in.ChangeDashboardCommand;
import com.toyproject.hyeonworld.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import com.toyproject.hyeonworld.api.partyDashboard.infrastructure.PartyDashboardRepository;

import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PartyDashboardService {
  private final PartyDashboardRepository partyDashboardRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public PartyDashboardInfo createPartyDashboard(long partyId) {
    try {
      return PartyDashboardInfo.from(partyDashboardRepository.save(PartyDashboardInfo.toEntity(partyId)));
    } catch (Exception e) {
      log.error("Failed to save PartyDashboard for partyId: {}", partyId, e);
      throw e;
    }
  }

  @Caching(
      put = @CachePut(cacheNames = "partyDashboardInfo", key = "#command.partyId")
  )
  @Transactional
  public PartyDashboardInfo changeDashboard(ChangeDashboardCommand command) {
    return PartyDashboardInfo.from(partyDashboardRepository.save(PartyDashboardInfo.toEntity(command)));
  }

  @Cacheable(cacheNames = "partyDashboardInfo", cacheManager = "caffeineCacheManager")
  public PartyDashboardInfo retrieve(long partyId) {
    return PartyDashboardInfo.from(partyDashboardRepository.findById(partyId)
        .orElseThrow(()->new ServerException(ServerResponseCode.PARTY_DASHBOARD_NOT_FOUND)));
  }
}
