package com.toyproject.hyeonworld.repository;

import com.toyproject.hyeonworld.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {



}
