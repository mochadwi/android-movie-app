package io.mochadwi.data.net;

import java.util.List;

import io.mochadwi.data.entity.BaseEntity;
import io.mochadwi.data.entity.MovieEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * RestApi for retrieving data from the network.
 */
public interface RetrofitMovie {

    /** Api url for getting all movies */
    String API_URL_GET_MOVIE_POPULAR_LIST = "movie/popular?api_key" +
        "=334879b2c8dc36a9f2c64f7bd4f0c91d";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MovieEntity}.
     */
    Observable<List<MovieEntity>> movieEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MovieEntity}.
     */
    @GET(API_URL_GET_MOVIE_POPULAR_LIST)
    Observable<BaseEntity<MovieEntity>> popularList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link MovieEntity}.
     *
     * @param movieId The movie id used to get movie data.
     */
    Observable<MovieEntity> movieEntityById(final int movieId);
}
