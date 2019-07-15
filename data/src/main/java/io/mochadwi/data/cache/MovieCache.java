package io.mochadwi.data.cache;

import io.mochadwi.data.entity.MovieEntity;
import io.reactivex.Observable;

/**
 * An interface representing a movie Cache.
 */
public interface MovieCache {

    /**
     * Gets an {@link Observable} which will emit a {@link MovieEntity}.
     *
     * @param movieId The movie id to retrieve data.
     */
    Observable<MovieEntity> get(final int movieId);

    /**
     * Puts and element into the cache.
     *
     * @param movieEntity Element to insert in the cache.
     */
    void put(MovieEntity movieEntity);

    /**
     * Checks if an element (Movie) exists in the cache.
     *
     * @param movieId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final int movieId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
