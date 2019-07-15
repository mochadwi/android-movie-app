package io.mochadwi.data.repository.datasource;

import java.util.List;

import io.mochadwi.data.cache.MovieCache;
import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.net.RestApi;
import io.mochadwi.data.net.RestApiMovie;
import io.reactivex.Observable;

/**
 * {@link MovieDataStore} implementation based on connections to the api (Cloud).
 */
class CloudMovieDataStore implements MovieDataStore {

    private final MovieCache movieCache;

    private final RestApiMovie restApi;

    /**
     * Construct a {@link MovieDataStore} based on connections to the api (Cloud).
     *
     * @param restApi    The {@link RestApi} implementation to use.
     * @param movieCache A {@link MovieCache} to cache data retrieved from the api.
     */
    CloudMovieDataStore(RestApiMovie restApi, MovieCache movieCache) {
        this.restApi = restApi;
        this.movieCache = movieCache;
    }

    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        return this.restApi.movieEntityList();
    }

    @Override
    public Observable<MovieEntity> movieEntityDetails(final int movieId) {
        return this.restApi.movieEntityById(movieId)
            .doOnNext(CloudMovieDataStore.this.movieCache::put);
    }
}
