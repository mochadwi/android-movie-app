package io.mochadwi.domain.repository;

import java.util.List;

import io.mochadwi.domain.Movie;
import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Movie} related data.
 */
public interface MovieRepository {

    /**
     * Get an {@link Observable} which will emit a List of {@link Movie}.
     */
    Observable<List<Movie>> movies();

    /**
     * Get an {@link Observable} which will emit a List of {@link Movie}.
     */
    Observable<List<Movie>> populars();

    /**
     * Get an {@link Observable} which will emit a {@link Movie}.
     *
     * @param movieId The movie id used to retrieve movie data.
     */
    Observable<Movie> movie(final int movieId);
}
