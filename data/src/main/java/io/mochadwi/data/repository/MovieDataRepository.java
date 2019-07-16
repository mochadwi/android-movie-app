package io.mochadwi.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mochadwi.data.entity.mapper.MovieEntityDataMapper;
import io.mochadwi.data.repository.datasource.MovieDataStore;
import io.mochadwi.data.repository.datasource.MovieDataStoreFactory;
import io.mochadwi.domain.Movie;
import io.mochadwi.domain.repository.MovieRepository;
import io.reactivex.Observable;

/**
 * {@link MovieRepository} for retrieving movie data.
 */
@Singleton
public class MovieDataRepository implements MovieRepository {

    private final MovieDataStoreFactory movieDataStoreFactory;

    private final MovieEntityDataMapper movieEntityDataMapper;

    /**
     * Constructs a {@link MovieRepository}.
     *
     * @param dataStoreFactory      A factory to construct different data source implementations.
     * @param movieEntityDataMapper {@link MovieEntityDataMapper}.
     */
    @Inject
    MovieDataRepository(MovieDataStoreFactory dataStoreFactory,
        MovieEntityDataMapper movieEntityDataMapper) {
        this.movieDataStoreFactory = dataStoreFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
    }

    @Override
    public Observable<List<Movie>> movies() {
        //we always get all movies from the cloud
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.createCloudDataStore();
        return movieDataStore.movieEntityList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Movie>> populars() {
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.createCloudDataStore();
        return movieDataStore.popularList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<Movie> movie(int movieId) {
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.create(movieId);
        return movieDataStore.movieEntityDetails(movieId)
            .map(this.movieEntityDataMapper::transform);
    }
}
