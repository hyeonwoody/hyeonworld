package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.DTO.Submission.SubmissionDTO;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionEssential;
import com.toyproject.hyeonworld.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    @PostMapping("/0")
    public ResponseEntity<Long> submission0 (@RequestBody SubmissionDTO submissionDTO){
        Long Id = submissionService.post(submissionDTO);
        return ResponseEntity.ok (Id);
    }

    @GetMapping("/0")
    public ResponseEntity<Map<String, SubmissionEssential> > submission0 (@RequestParam Long memberId){
        System.out.println("GET Submission memberId: "+ memberId);
        Map<String, SubmissionEssential> submissionEssentialMap;

        if (true){
            submissionEssentialMap = submissionService.get();
            return ResponseEntity.ok (submissionEssentialMap);
        }
        else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/zz")
    public ResponseEntity<Long> submission1(){
        System.out.println("INIT MEMBER");

        return ResponseEntity.ok (1L);
    }

}
