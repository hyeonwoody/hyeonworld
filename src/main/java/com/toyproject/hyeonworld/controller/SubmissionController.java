package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    @PostMapping("/0")
    public ResponseEntity<Long> submission0 (@RequestParam Long memberId, @RequestParam List<String> input, @RequestParam Integer inputFalse){
        System.out.println("memberId: "+ memberId);
        System.out.println("INput: "+ input);
        System.out.println("inputFalse : "+ inputFalse);
        Long Id = submissionService.post(0, memberId, input, inputFalse);
        return ResponseEntity.ok (Id);
    }

    @GetMapping("/0")
    public ResponseEntity<Map<String, Submission> > submission0 (@RequestParam Long memberId){
        System.out.println("GET Submission memberId: "+ memberId);
        Map<String, Submission> submissionList;
        //List<Submission> submissionList;
        if (memberId == 1219L){
            System.out.println("HERE");
            submissionList = submissionService.get(0);

            return ResponseEntity.ok (submissionList);
        }
        else {
            System.out.println("NULL");
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/zz")
    public ResponseEntity<Long> submission1(){
        System.out.println("INIT MEMBER");

        return ResponseEntity.ok (1L);
    }

}
