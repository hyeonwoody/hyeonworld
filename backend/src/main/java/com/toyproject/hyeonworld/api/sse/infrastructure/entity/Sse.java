package com.toyproject.hyeonworld.api.sse.infrastructure.entity;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score.ScoreBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sse")
public class Sse {
    @Id
    Long id;
    Long partyId;

    public static SseBuilder defaultBuilder(){
        return Sse.builder();
    }
}
