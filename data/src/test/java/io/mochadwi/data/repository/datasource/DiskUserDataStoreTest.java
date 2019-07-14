package io.mochadwi.data.repository.datasource;

import io.mochadwi.data.cache.UserCache;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskUserDataStoreTest {

  private static final int FAKE_USER_ID = 11;

  private DiskUserDataStore diskUserDataStore;

  @Mock private UserCache mockUserCache;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    diskUserDataStore = new DiskUserDataStore(mockUserCache);
  }

  @Test
  public void testGetUserEntityListUnsupported() {
    expectedException.expect(UnsupportedOperationException.class);
    diskUserDataStore.userEntityList();
  }

  @Test
  public void testGetUserEntityDetailesFromCache() {
    diskUserDataStore.userEntityDetails(FAKE_USER_ID);
    verify(mockUserCache).get(FAKE_USER_ID);
  }
}
