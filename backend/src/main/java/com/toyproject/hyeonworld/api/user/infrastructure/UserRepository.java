package com.toyproject.hyeonworld.api.user.infrastructure;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserRepository {

  User save(User user);

  Optional<User> findById(long userId);
}
