package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

}
