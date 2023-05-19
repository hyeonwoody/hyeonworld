package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName (String name);



}
