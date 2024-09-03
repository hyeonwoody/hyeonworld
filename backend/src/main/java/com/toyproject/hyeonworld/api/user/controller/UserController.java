package com.toyproject.hyeonworld.api.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.hyeonworld.DTO.Member.MemberCreateDTO;
import com.toyproject.hyeonworld.api.user.controller.dto.req.UserRequest;
import com.toyproject.hyeonworld.api.user.controller.dto.res.UserResponse;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest.create request){
    return ResponseEntity.ok(UserResponse.from(userService.createUser(request.toCommand())));
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> retrieveAllUsers(){
    return ResponseEntity.ok(UserResponse.from(userService.getAllUsers()));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserResponse> retrieveUser(@PathVariable("userId") long userId){
    return ResponseEntity.ok(UserResponse.from(userService.getUserById(userId)));
  }

  @PatchMapping("/{userId}")
  public ResponseEntity<UserResponse> editUser(
      @PathVariable long userId,
      @RequestBody UserRequest.edit request){
    return ResponseEntity.ok(UserResponse.from(userService.editUser(request.toCommand(userId))));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<UserResponse> deleteUser(
      @PathVariable long userId){
    return ResponseEntity.ok(UserResponse.from(userService.deleteUser(userId)));
  }

  @PatchMapping("/init")
  public ResponseEntity<Integer> initUsers(){
    return ResponseEntity.ok(userService.initUsers());
  }
}
