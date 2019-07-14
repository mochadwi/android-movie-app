package io.mochadwi.data.repository;

import io.mochadwi.data.entity.mapper.UserEntityDataMapper;
import io.mochadwi.data.repository.datasource.UserDataStore;
import io.mochadwi.data.repository.datasource.UserDataStoreFactory;
import io.mochadwi.domain.User;
import io.mochadwi.domain.repository.UserRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final UserEntityDataMapper userEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param userEntityDataMapper {@link UserEntityDataMapper}.
   */
  @Inject
  UserDataRepository(UserDataStoreFactory dataStoreFactory,
      UserEntityDataMapper userEntityDataMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    this.userEntityDataMapper = userEntityDataMapper;
  }

  @Override public Observable<List<User>> users() {
    //we always get all users from the cloud
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
    return userDataStore.userEntityList().map(this.userEntityDataMapper::transform);
  }

  @Override public Observable<User> user(int userId) {
    final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
    return userDataStore.userEntityDetails(userId).map(this.userEntityDataMapper::transform);
  }
}
