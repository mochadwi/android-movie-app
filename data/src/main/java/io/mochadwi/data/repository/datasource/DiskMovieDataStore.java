package io.mochadwi.data.repository.datasource;

import java.util.List;

import io.mochadwi.data.cache.MovieCache;
import io.mochadwi.data.entity.MovieEntity;
import io.reactivex.Observable;

/**
 * {@link MovieDataStore} implementation based on file system data store.
 */
class DiskMovieDataStore implements MovieDataStore {

    private final MovieCache movieCache;

    /**
     * Construct a {@link MovieDataStore} based file system data store.
     *
     * @param movieCache A {@link MovieCache} to cache data retrieved from the api.
     */
    DiskMovieDataStore(MovieCache movieCache) {
        this.movieCache = movieCache;
    }

    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of movies.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<MovieEntity> movieEntityDetails(final int movieId) {
        return this.movieCache.get(movieId);
    }
}
