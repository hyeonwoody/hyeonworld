package com.toyproject.hyeonworld.api.user.domain;
import static com.toyproject.hyeonworld.api.user.infrastructure.entity.User.*;
import static com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo.*;
import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;

import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.infrastructure.UserRepository;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
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

  public UserInfo getUserById(long userId) {
    return from(userRepository.findById(userId)
        .orElseThrow(()->new ServerException(ServerResponseCode.USER_NOT_FOUND))
    );
  }
}
