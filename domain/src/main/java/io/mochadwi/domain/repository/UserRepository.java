package io.mochadwi.domain.repository;

import io.mochadwi.domain.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link User}.
   */
  Observable<List<User>> users();

  /**
   * Get an {@link Observable} which will emit a {@link User}.
   *
   * @param userId The user id used to retrieve user data.
   */
  Observable<User> user(final int userId);
}
