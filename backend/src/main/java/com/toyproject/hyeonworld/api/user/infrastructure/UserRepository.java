package com.toyproject.hyeonworld.api.user.infrastructure;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.List;
import java.util.Optional;

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
}
