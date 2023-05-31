package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.SubmissionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final MemberRepository memberRepository;

    public SubmissionService(SubmissionRepository submissionRepository, MemberRepository memberRepository) {
        this.submissionRepository = submissionRepository;
        this.memberRepository = memberRepository;
    }

    public Long post(int game, Long memberId, List<String> input, Integer inputFalse) {
        Submission submission = new Submission();
        submission.setGame(game);
        submission.setNumber(inputFalse);
            String str = String.join(";", input);
        submission.setText(str);
            Optional<Member> member = memberRepository.findById(memberId);
        Member foundMember = member.orElseThrow(() -> new IllegalArgumentException("Member not found"));
        submission.setMember(foundMember);
        submissionRepository.save(submission);
        memberRepository.save(foundMember);
        return foundMember.getId();
    }

}
