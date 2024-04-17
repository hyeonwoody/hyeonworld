package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.Submission.SubmissionDTO;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionEssential;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.member.JdbcTemplateMemberRepository;
import com.toyproject.hyeonworld.repository.submission.JdbcTemplateSubmissionRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubmissionService {

    private final JdbcTemplateSubmissionRepository jdbcSubmissionRepository;


    private final JdbcTemplateMemberRepository jdbcTemplateMemberRepository;
    public SubmissionService(JdbcTemplateSubmissionRepository jdbcSubmissionRepository,
                             JdbcTemplateMemberRepository jdbcTemplateMemberRepository) {

        this.jdbcSubmissionRepository = jdbcSubmissionRepository;

        this.jdbcTemplateMemberRepository = jdbcTemplateMemberRepository;
    }

    public Long post(SubmissionDTO submissionDTO) {
        Submission submission = new Submission(submissionDTO.getGame(), submissionDTO.getNumber(), submissionDTO.getText());

        jdbcSubmissionRepository.insert(submissionDTO);
        return submissionDTO.getMemberId();
    }

    public Map<String, SubmissionEssential>  get() {
        Map<String, SubmissionEssential> ret = new HashMap<>();
        List <Member> loginMemberList = jdbcTemplateMemberRepository.getLoginMemberIds();
        for (Member member : loginMemberList){

            Submission submission = jdbcSubmissionRepository.findByMemberId(member.getId());
            if (submission != null){
                continue;
            }
            SubmissionEssential submissionEssential = new SubmissionEssential(submission.getNumber(), submission.getText());
            ret.put(member.getName(), submissionEssential);
        }
        System.out.println("SIZE : "+ ret.size());
        return ret;
    }
}
