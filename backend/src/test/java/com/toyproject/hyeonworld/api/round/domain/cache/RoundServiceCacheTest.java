package com.toyproject.hyeonworld.api.round.domain.cache;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.github.benmanes.caffeine.cache.Cache;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.infrastructure.RoundRepository;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Optional;

@SpringBootTest
@EnableCaching
//@ContextConfiguration(classes = {CacheCaffeineConfig.class}) // Make sure to include your cache configuration
class RoundServiceCacheTest {

  @Autowired
  private RoundService service;

  @SpyBean
  private RoundRepository roundRepository;

  @Autowired
  private CacheManager cacheManager;

  private long partyId;
  private long gameId;

  @BeforeEach
  void setUp() {
    partyId = 4L;
    gameId = 1L;
    cacheManager.getCache("roundGame").clear();
  }

  @Test
  void begin_ShouldEvictAndInsertCache(){
    BeginRoundCommand command = new BeginRoundCommand(partyId, gameId);
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.insert(mockRound)).thenReturn(mockRound);

    RoundInfo mockRoundInfo = RoundInfo.from(mockRound);
    cacheManager.getCache("roundInfo").put(partyId, mockRoundInfo);

    RoundInfo result = service.begin(command);

    assertNotNull(result);

    verify(roundRepository, times(1)).insert(any(Round.class));
    assertNull(cacheManager.getCache("roundInfo").get(partyId));
  }

  @Test
  void retrieveCurrentRound_ShuldCacheAndUseCache(){
    long retrievePartyId = 60L;
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findByPartyId(retrievePartyId)).thenReturn(Optional.of(mockRound));

    RoundInfo roundInfo1 = service.retrieveCurrentRound(retrievePartyId);
    verify(roundRepository, times(1)).findByPartyId(retrievePartyId);
    assertEquals(-1, roundInfo1.getId());
    assertEquals("",roundInfo1.getAnswer());

    RoundInfo roundInfo2 = service.retrieveCurrentRound(retrievePartyId);
    verify(roundRepository, times(1)).findByPartyId(retrievePartyId);
    assertEquals(-1, roundInfo2.getId());
    assertEquals("",roundInfo2.getAnswer());

    Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache("roundInfo").getNativeCache();
    assertEquals(roundInfo1, nativeCache.getIfPresent(retrievePartyId));
  }

  @Test
  void retrieveCurrentGame_ShouldCacheAndUseCache() {
    // Arrange
    long roundId = 1L;
    long gameId = 100L;
    Round mockRound = new Round();
    mockRound.setId(roundId);
    mockRound.setGameId(gameId);

    when(roundRepository.findById(roundId)).thenReturn(Optional.of(mockRound));

    // Act - First call (should hit the repository)
    long result1 = service.retrieveCurrentGame(roundId);
    assertEquals(gameId, result1);
    verify(roundRepository, times(1)).findById(roundId);

    // Act - Second call (should use cache)
    long result2 = service.retrieveCurrentGame(roundId);
    assertEquals(gameId, result2);
    verify(roundRepository, times(1)).findById(roundId);

    // Verify cache contents
    Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache("roundGame").getNativeCache();
    assertEquals(gameId, nativeCache.getIfPresent(roundId));
  }

  @Test
  void retrieveAnswer_ShouldCacheAndUseCache() {
    // Arrange
    long roundId = 1L;
    long gameId = 100L;
    String answer = "prison_break";
    Round mockRound = new Round();
    mockRound.setId(roundId);
    mockRound.setGameId(gameId);
    mockRound.setAnswer(answer);

    when(roundRepository.findById(roundId)).thenReturn(Optional.of(mockRound));

    // Act - First call (should hit the repository)
    String result1 = service.retrieveAnswer(roundId);
    assertEquals(answer, result1);
    verify(roundRepository, times(1)).findById(roundId);

    // Act - Second call (should use cache)
    String result2 = service.retrieveAnswer(roundId);
    assertEquals(answer, result2);
    verify(roundRepository, times(1)).findById(roundId);

    // Verify cache contents
    Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache("roundAnswer").getNativeCache();
    assertEquals(answer, nativeCache.getIfPresent(roundId));
  }
}