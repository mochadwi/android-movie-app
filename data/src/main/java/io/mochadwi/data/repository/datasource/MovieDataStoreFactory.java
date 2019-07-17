package io.mochadwi.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mochadwi.data.cache.MovieCache;
import io.mochadwi.data.net.RetrofitMovie;
import io.mochadwi.data.net.RetrofitMovieImpl;

/**
 * Factory that creates different implementations of {@link MovieDataStore}.
 */
@Singleton
public class MovieDataStoreFactory {

    private final Context context;

    private final MovieCache movieCache;

    @Inject
    MovieDataStoreFactory(@NonNull Context context, @NonNull MovieCache movieCache) {
        this.context = context.getApplicationContext();
        this.movieCache = movieCache;
    }

    /**
     * Create {@link MovieDataStore} from a movie id.
     */
    public MovieDataStore create(int movieId) {
        MovieDataStore movieDataStore;

        if (!this.movieCache.isExpired() && this.movieCache.isCached(movieId)) {
            movieDataStore = new DiskMovieDataStore(this.movieCache);
        } else {
            movieDataStore = createCloudDataStore();
        }

        return movieDataStore;
    }

    /**
     * Create {@link MovieDataStore} to retrieve data from the Cloud.
     */
    public MovieDataStore createCloudDataStore() {
        final RetrofitMovie restApi = new RetrofitMovieImpl(this.context);

        return new CloudMovieDataStore(restApi, this.movieCache);
    }
}
