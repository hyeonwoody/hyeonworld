package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query ("SELECT e FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")
    Optional<Party> findRecentOne();

    @Query ("SELECT e.currentGame FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")
    Integer getCurrentGame();

    @Query ("SELECT e.currentGameStage FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")
    Integer getCurrentGameStage();
}
