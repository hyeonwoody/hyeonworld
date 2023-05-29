package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName (String name);

    @Modifying
    @Query("UPDATE Member e SET e.login = false")
    void updateLoginAndInGameColumns();

//    @Modifying
//    @Query("UPDATE Member e SET e.login =:true WHERE e.id = :id")
//    void updateLoginById (Long id);
//
//
//    @Query ("SELECT e.id FROM Member e WHERE e.name = :memberName")
//    Long findIdByName(String memberName);
//
//    @Query("UPDATE Member e SET e.login =:false WHERE e.id = :id")
//    Optional<Member> updateLogoutById(Long id);
}
