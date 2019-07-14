package io.mochadwi.data.repository.datasource;

import io.mochadwi.data.cache.UserCache;
import io.mochadwi.data.entity.UserEntity;
import io.mochadwi.data.net.RestApi;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudUserDataStoreTest {

  private static final int FAKE_USER_ID = 765;

  private CloudUserDataStore cloudUserDataStore;

  @Mock private RestApi mockRestApi;
  @Mock private UserCache mockUserCache;

  @Before
  public void setUp() {
    cloudUserDataStore = new CloudUserDataStore(mockRestApi, mockUserCache);
  }

  @Test
  public void testGetUserEntityListFromApi() {
    cloudUserDataStore.userEntityList();
    verify(mockRestApi).userEntityList();
  }

  @Test
  public void testGetUserEntityDetailsFromApi() {
    UserEntity fakeUserEntity = new UserEntity();
    Observable<UserEntity> fakeObservable = Observable.just(fakeUserEntity);
    given(mockRestApi.userEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

    cloudUserDataStore.userEntityDetails(FAKE_USER_ID);

    verify(mockRestApi).userEntityById(FAKE_USER_ID);
  }
}
