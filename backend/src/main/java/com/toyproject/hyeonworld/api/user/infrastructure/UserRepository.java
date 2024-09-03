package com.toyproject.hyeonworld.api.user.infrastructure;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserRepository {

  User save(User user);
}
