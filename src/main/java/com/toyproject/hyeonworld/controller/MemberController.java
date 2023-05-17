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


    @GetMapping("/login-confirm")
    public ResponseEntity<String> loginConfirm (HttpServletRequest request, @RequestParam String loginName){
        System.out.println("여기 왔다aaaaa");
        System.out.println(request.getParameterMap());
        System.out.println(request.getMethod());
        System.out.println(loginName);
        //memberService.loginConfirm (request.getMethod());
        return ResponseEntity.ok (request.getRemoteAddr());
    }

}
