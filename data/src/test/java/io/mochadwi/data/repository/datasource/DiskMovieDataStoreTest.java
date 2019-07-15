package io.mochadwi.data.repository.datasource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.mochadwi.data.cache.MovieCache;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskMovieDataStoreTest {

    private static final int FAKE_USER_ID = 11;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DiskMovieDataStore diskMovieDataStore;

    @Mock
    private MovieCache mockMovieCache;

    @Before
    public void setUp() {
        diskMovieDataStore = new DiskMovieDataStore(mockMovieCache);
    }

    @Test
    public void testGetMovieEntityListUnsupported() {
        expectedException.expect(UnsupportedOperationException.class);
        diskMovieDataStore.movieEntityList();
    }

    @Test
    public void testGetMovieEntityDetailesFromCache() {
        diskMovieDataStore.movieEntityDetails(FAKE_USER_ID);
        verify(mockMovieCache).get(FAKE_USER_ID);
    }
}
