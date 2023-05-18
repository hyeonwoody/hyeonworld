package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Member;
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


    @GetMapping("/login-confirm")
    public ResponseEntity<Boolean> loginConfirm (HttpServletRequest request, @RequestParam String loginName){
        System.out.println("여기 왔다aaaaa");

        Member loginMember = memberService.login (loginName);

        boolean success = false;
        if (loginMember != null)
            success = true;
        return ResponseEntity.ok (success);
    }

    @GetMapping("/logout-confirm")
    public ResponseEntity<Boolean> logoutConfirm (HttpServletRequest request, @RequestParam String logoutName){
        System.out.println("여기 왔다aaaaa");

        Member logoutMember = memberService.logout (logoutName);
        return ResponseEntity.ok (true);
    }

}
