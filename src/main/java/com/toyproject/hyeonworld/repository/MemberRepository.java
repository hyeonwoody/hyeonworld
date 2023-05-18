package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName (String name);

    default void login(Member member){
        member.setLogin(true);
    };
}
