package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.SubmissionDTO;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.SubmissionRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final MemberRepository memberRepository;

    public SubmissionService(SubmissionRepository submissionRepository, MemberRepository memberRepository) {
        this.submissionRepository = submissionRepository;
        this.memberRepository = memberRepository;
    }

    public Long post(SubmissionDTO submissionDTO) {
        Submission submission = new Submission();
        submission.setGame(submissionDTO.getGame());
        submission.setNumber(submissionDTO.getNumber());

        submission.setText(submissionDTO.getText());
        Optional<Member> member = memberRepository.findById(submissionDTO.getMemberId());
        Member foundMember = member.orElseThrow(() -> new IllegalArgumentException("Member not found"+submissionDTO.getMemberId()));
        submission.setMember(foundMember);
        submissionRepository.save(submission);
        memberRepository.save(foundMember);
        return foundMember.getId();
    }

    public Long post(int game, Long memberId, List<String> input, Integer inputFalse) {
        Submission submission = new Submission();
        submission.setGame(game);
        submission.setNumber(inputFalse);
            String str = String.join(";", input);
        submission.setText(str);
            Optional<Member> member = memberRepository.findById(memberId);
        Member foundMember = member.orElseThrow(() -> new IllegalArgumentException("Member not found"+memberId));
        submission.setMember(foundMember);
        submissionRepository.save(submission);
        memberRepository.save(foundMember);
        return foundMember.getId();
    }

    public Map<String, Submission>  get(int game) {
        Map<String, Submission> ret = new HashMap<>();
        List<Submission> ret1 = new ArrayList<>();
        List <Member> loginMemberList = memberRepository.findAll().stream()
                                            .filter(member -> member.getLogin())
                                            .collect(Collectors.toList());

        for (Member member : loginMemberList){
            List <Submission> submissionList = member.getSubmissionList();

            if (!submissionList.isEmpty()){
                System.out.println("FFFFFFF");
                Submission submission = new Submission();
                Submission recentlyAddedSubmission = submissionList.get(submissionList.size() - 1);

                submission.setAll(recentlyAddedSubmission);
                ret1.add(submission);


                ret.put(member.getName(), submission);
            }

//            submission.setText("WORKING");
//            submission.setId(22L);

        }

        return ret;
    }
}
