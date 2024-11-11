package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public interface ScoreHistoryRepository {
    ScoreHistory save(ScoreHistory scoreHistory);

    List<ScoreHistory> saveAll(List<ScoreHistory> scoreHistory);

    List<ScoreHistory> findByPartyId(long partyId);
}
