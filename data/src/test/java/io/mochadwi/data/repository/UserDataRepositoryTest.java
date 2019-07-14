package io.mochadwi.data.repository;

import io.mochadwi.data.entity.UserEntity;
import io.mochadwi.data.entity.mapper.UserEntityDataMapper;
import io.mochadwi.data.repository.datasource.UserDataStore;
import io.mochadwi.data.repository.datasource.UserDataStoreFactory;
import io.mochadwi.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDataRepositoryTest {

  private static final int FAKE_USER_ID = 123;

  @Mock
  private User mockUser;

  @Mock
  private UserDataStore mockUserDataStore;

  @Mock private UserDataStoreFactory mockUserDataStoreFactory;

  @Mock private UserEntity mockUserEntity;

  @Mock
  private UserEntityDataMapper mockUserEntityDataMapper;

  private UserDataRepository userDataRepository;

  @Before
  public void setUp() {
    userDataRepository = new UserDataRepository(mockUserDataStoreFactory, mockUserEntityDataMapper);
    given(mockUserDataStoreFactory.create(anyInt())).willReturn(mockUserDataStore);
    given(mockUserDataStoreFactory.createCloudDataStore()).willReturn(mockUserDataStore);
  }

  @Test
  public void testGetUsersHappyCase() {
    List<UserEntity> usersList = new ArrayList<>();
    usersList.add(new UserEntity());
    given(mockUserDataStore.userEntityList()).willReturn(Observable.just(usersList));

    userDataRepository.users();

    verify(mockUserDataStoreFactory).createCloudDataStore();
    verify(mockUserDataStore).userEntityList();
  }

  @Test
  public void testGetUserHappyCase() {
    UserEntity userEntity = new UserEntity();
    given(mockUserDataStore.userEntityDetails(FAKE_USER_ID)).willReturn(Observable.just(userEntity));
    userDataRepository.user(FAKE_USER_ID);

    verify(mockUserDataStoreFactory).create(FAKE_USER_ID);
    verify(mockUserDataStore).userEntityDetails(FAKE_USER_ID);
  }
}
