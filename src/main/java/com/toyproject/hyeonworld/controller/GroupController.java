package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Group;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.service.GroupService;
import com.toyproject.hyeonworld.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("/init")
    public ResponseEntity<Boolean> initGroup (HttpServletRequest request, @RequestParam Integer group, @RequestParam Integer persons){
        Group newGroup = new Group();
        System.out.println("왔다잉");
        newGroup.setGroup(group);
        newGroup.setPersons(persons);
        groupService.init(newGroup);
        return ResponseEntity.ok (true);
    }

}