package io.mochadwi.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.entity.mapper.MovieEntityDataMapper;
import io.mochadwi.data.repository.datasource.MovieDataStore;
import io.mochadwi.data.repository.datasource.MovieDataStoreFactory;
import io.mochadwi.domain.Movie;
import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieDataRepositoryTest {

    private static final int FAKE_USER_ID = 123;

    @Mock
    private Movie mockMovie;

    @Mock
    private MovieDataStore mockMovieDataStore;

    @Mock
    private MovieDataStoreFactory mockMovieDataStoreFactory;

    @Mock
    private MovieEntity mockMovieEntity;

    @Mock
    private MovieEntityDataMapper mockMovieEntityDataMapper;

    private MovieDataRepository movieDataRepository;

    @Before
    public void setUp() {
        movieDataRepository = new MovieDataRepository(mockMovieDataStoreFactory,
            mockMovieEntityDataMapper);
        given(mockMovieDataStoreFactory.create(anyInt())).willReturn(mockMovieDataStore);
        given(mockMovieDataStoreFactory.createCloudDataStore()).willReturn(mockMovieDataStore);
    }

    @Test
    public void testGetMoviesHappyCase() {
        List<MovieEntity> moviesList = new ArrayList<>();
        moviesList.add(new MovieEntity());
        given(mockMovieDataStore.movieEntityList()).willReturn(Observable.just(moviesList));

        movieDataRepository.movies();

        verify(mockMovieDataStoreFactory).createCloudDataStore();
        verify(mockMovieDataStore).movieEntityList();
    }

    @Test
    public void testGetMovieHappyCase() {
        MovieEntity movieEntity = new MovieEntity();
        given(mockMovieDataStore.movieEntityDetails(FAKE_USER_ID))
            .willReturn(Observable.just(movieEntity));
        movieDataRepository.movie(FAKE_USER_ID);

        verify(mockMovieDataStoreFactory).create(FAKE_USER_ID);
        verify(mockMovieDataStore).movieEntityDetails(FAKE_USER_ID);
    }
}
