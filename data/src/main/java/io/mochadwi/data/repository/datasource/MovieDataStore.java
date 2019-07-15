package io.mochadwi.data.repository.datasource;

import java.util.List;

import io.mochadwi.data.entity.MovieEntity;
import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface MovieDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link MovieEntity}.
     */
    Observable<List<MovieEntity>> movieEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link MovieEntity} by its id.
     *
     * @param movieId The id to retrieve movie data.
     */
    Observable<MovieEntity> movieEntityDetails(final int movieId);
}
