package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    @PostMapping("/0")
    public ResponseEntity<Long> submission0 (@RequestParam Long memberId,@RequestParam List<String> input,@RequestParam Integer inputFalse){
        System.out.println("memberId: "+ memberId);
        System.out.println("INput: "+ input);
        System.out.println("inputFalse : "+ inputFalse);
        Long Id = submissionService.post(0, memberId, input, inputFalse);
        return ResponseEntity.ok (Id);
    }

    @PostMapping("/zz")
    public ResponseEntity<Long> submission1(){
        System.out.println("INIT MEMBER");

        return ResponseEntity.ok (1L);
    }

}
