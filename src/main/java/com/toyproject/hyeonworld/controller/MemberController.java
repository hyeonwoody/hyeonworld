package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PutMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (HttpServletRequest request, @RequestParam String loginName){
        Long loginMemberId = memberService.login (loginName);
        return ResponseEntity.ok (loginMemberId);
    }

    @PutMapping("/logout-confirm")
    public ResponseEntity<Long> logoutConfirm (HttpServletRequest request, @RequestParam Long logoutId){
        Long logoutMemberId = memberService.logout (logoutId);
        return ResponseEntity.ok (logoutMemberId);
    }

}
