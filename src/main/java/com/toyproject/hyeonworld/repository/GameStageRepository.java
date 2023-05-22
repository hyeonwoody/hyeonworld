package com.toyproject.hyeonworld.repository;


import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.GameStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStageRepository extends JpaRepository<GameStage, Long> {
}
