package io.mochadwi.domain.interactor;

import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUserListTest {

  private GetUserList getUserList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private UserRepository mockUserRepository;

  @Before
  public void setUp() {
    getUserList = new GetUserList(mockUserRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetUserListUseCaseObservableHappyCase() {
    getUserList.buildUseCaseObservable(null);

    verify(mockUserRepository).users();
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
