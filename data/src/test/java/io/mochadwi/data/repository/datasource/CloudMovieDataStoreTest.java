package io.mochadwi.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.mochadwi.data.cache.MovieCache;
import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.net.RestApiMovie;
import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudMovieDataStoreTest {

    private static final int FAKE_USER_ID = 765;

    private CloudMovieDataStore cloudMovieDataStore;

    @Mock
    private MovieCache mockMovieCache;

    @Mock
    private RestApiMovie mockRestApi;

    @Before
    public void setUp() {
        cloudMovieDataStore = new CloudMovieDataStore(mockRestApi, mockMovieCache);
    }

    @Test
    public void testGetMovieEntityListFromApi() {
        cloudMovieDataStore.movieEntityList();
        verify(mockRestApi).movieEntityList();
    }

    @Test
    public void testGetPopularEntityListFromApi() {
        cloudMovieDataStore.popularList();
        verify(mockRestApi).popularList();
    }

    @Test
    public void testGetMovieEntityDetailsFromApi() {
        MovieEntity fakeMovieEntity = new MovieEntity();
        Observable<MovieEntity> fakeObservable = Observable.just(fakeMovieEntity);
        given(mockRestApi.movieEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

        cloudMovieDataStore.movieEntityDetails(FAKE_USER_ID);

        verify(mockRestApi).movieEntityById(FAKE_USER_ID);
    }
}
