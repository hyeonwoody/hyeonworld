package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import com.toyproject.hyeonworld.api.score.infarstructure.jdbc.ScoreHistoryJdbcTemplateRepository;
import com.toyproject.hyeonworld.api.score.infarstructure.jpa.ScoreHistoryJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class ScoreHistoryRepositoryImpl implements ScoreHistoryRepository {
    private final ScoreHistoryJpaRepository scoreHistoryJpaRepository;
    private final ScoreHistoryJdbcTemplateRepository scoreHistoryJdbcTemplateRepository;

    @Override
    public ScoreHistory save(ScoreHistory scoreHistory){
        return scoreHistoryJpaRepository.save(scoreHistory);
    }

    @Override
    public List<ScoreHistory> saveAll(List<ScoreHistory> scoreHistory){
        return scoreHistoryJdbcTemplateRepository.saveAll(scoreHistory);
    }

    @Override
    public List<ScoreHistory> findByPartyId(long partyId) {
        return scoreHistoryJdbcTemplateRepository.findByPartyId(partyId);
    }
}
