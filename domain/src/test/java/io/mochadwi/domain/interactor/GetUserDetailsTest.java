package io.mochadwi.domain.interactor;

import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.interactor.GetUserDetails.Params;
import io.mochadwi.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUserDetailsTest {

  private static final int USER_ID = 123;

  private GetUserDetails getUserDetails;

  @Mock private UserRepository mockUserRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    getUserDetails = new GetUserDetails(mockUserRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetUserDetailsUseCaseObservableHappyCase() {
    getUserDetails.buildUseCaseObservable(Params.forUser(USER_ID));

    verify(mockUserRepository).user(USER_ID);
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }

  @Test
  public void testShouldFailWhenNoOrEmptyParameters() {
    expectedException.expect(NullPointerException.class);
    getUserDetails.buildUseCaseObservable(null);
  }
}
