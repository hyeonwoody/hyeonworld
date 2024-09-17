package com.toyproject.hyeonworld.api.user.infrastructure;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserRepository {

  User save(User user);

  Optional<User> findById(long userId);

  List<User> findAll();

  int deleteById(long userId);

  List<User> findByLogin(boolean login);

  Optional<User> findByName(String userName);

  List<UserNameProjection> findNamesByIds(Set<Long> userIds);
}
