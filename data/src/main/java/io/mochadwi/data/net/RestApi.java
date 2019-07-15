package io.mochadwi.data.net;

import java.util.List;

import io.mochadwi.data.entity.UserEntity;
import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL =
      "https://api.themoviedb.org/3/";

  /** Api url for getting a user profile: Remember to concatenate id + 'json' */
  String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

  /** Api url for getting all users */
  String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
   */
  Observable<List<UserEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
   *
   * @param userId The user id used to get user data.
   */
  Observable<UserEntity> userEntityById(final int userId);
}
