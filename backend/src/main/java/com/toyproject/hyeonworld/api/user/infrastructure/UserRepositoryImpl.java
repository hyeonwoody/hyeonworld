package com.toyproject.hyeonworld.api.user.infrastructure;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.api.user.infrastructure.jpa.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
  private final UserJpaRepository userJpaRepository;

  @Override
  public User save(User user) {
    return userJpaRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userJpaRepository.findAll();
  }

  @Override
  public Optional<User> findById(long userId) {
    return userJpaRepository.findById(userId);
  }

  @Override
  public int deleteById(long userId) {
    return userJpaRepository.deleteById(userId);
  }
}
