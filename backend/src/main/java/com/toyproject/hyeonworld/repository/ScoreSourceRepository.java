package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.ScoreSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreSourceRepository  extends JpaRepository <ScoreSource, Long> {
}
