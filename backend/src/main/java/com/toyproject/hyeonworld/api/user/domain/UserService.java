package com.toyproject.hyeonworld.api.user.domain;
import static com.toyproject.hyeonworld.api.user.infrastructure.entity.User.*;
import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo.*;
import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;

import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserInfo createUser(CreateUserCommand command) {
    return from(userRepository.save(create(command)));
  }
}
