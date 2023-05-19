package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/login-confirm")
    public ResponseEntity<Boolean> loginConfirm (HttpServletRequest request, @RequestParam String loginName){

        Member loginMember = memberService.login (loginName);

        boolean success = false;
        if (loginMember != null)
            success = true;
        return ResponseEntity.ok (success);
    }

    @GetMapping("/logout-confirm")
    public ResponseEntity<Boolean> logoutConfirm (HttpServletRequest request, @RequestParam String logoutName){
        Member logoutMember = memberService.logout (logoutName);
        return ResponseEntity.ok (true);
    }

}
