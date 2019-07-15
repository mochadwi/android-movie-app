package io.mochadwi.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import io.mochadwi.data.ApplicationTestCase;
import io.mochadwi.data.cache.MovieCache;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class MovieDataStoreFactoryTest extends ApplicationTestCase {

    private static final int FAKE_USER_ID = 11;

    @Mock
    private MovieCache mockMovieCache;

    private MovieDataStoreFactory movieDataStoreFactory;

    @Before
    public void setUp() {
        movieDataStoreFactory = new MovieDataStoreFactory(RuntimeEnvironment.application,
            mockMovieCache);
    }

    @Test
    public void testCreateDiskDataStore() {
        given(mockMovieCache.isCached(FAKE_USER_ID)).willReturn(true);
        given(mockMovieCache.isExpired()).willReturn(false);

        MovieDataStore movieDataStore = movieDataStoreFactory.create(FAKE_USER_ID);

        assertThat(movieDataStore, is(notNullValue()));
        assertThat(movieDataStore, is(instanceOf(DiskMovieDataStore.class)));

        verify(mockMovieCache).isCached(FAKE_USER_ID);
        verify(mockMovieCache).isExpired();
    }

    @Test
    public void testCreateCloudDataStore() {
        given(mockMovieCache.isExpired()).willReturn(true);
        given(mockMovieCache.isCached(FAKE_USER_ID)).willReturn(false);

        MovieDataStore movieDataStore = movieDataStoreFactory.create(FAKE_USER_ID);

        assertThat(movieDataStore, is(notNullValue()));
        assertThat(movieDataStore, is(instanceOf(CloudMovieDataStore.class)));

        verify(mockMovieCache).isExpired();
    }
}
