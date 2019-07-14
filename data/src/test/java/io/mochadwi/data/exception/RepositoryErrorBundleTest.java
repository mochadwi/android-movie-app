package io.mochadwi.data.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryErrorBundleTest {

  private RepositoryErrorBundle repositoryErrorBundle;

  @Mock private Exception mockException;

  @Before
  public void setUp() {
    repositoryErrorBundle = new RepositoryErrorBundle(mockException);
  }

  @Test
  @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
  public void testGetErrorMessageInteraction() {
    repositoryErrorBundle.getErrorMessage();

    verify(mockException).getMessage();
  }
}
