package io.mochadwi.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MalformedURLException;
import java.util.List;

import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.entity.mapper.MovieEntityJsonMapper;
import io.mochadwi.data.exception.NetworkConnectionException;
import io.reactivex.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiMovieImpl implements RestApiMovie {

    private final Context context;

    private final MovieEntityJsonMapper movieEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context               {@link Context}.
     * @param movieEntityJsonMapper {@link MovieEntityJsonMapper}.
     */
    public RestApiMovieImpl(Context context, MovieEntityJsonMapper movieEntityJsonMapper) {
        if (context == null || movieEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.movieEntityJsonMapper = movieEntityJsonMapper;
    }

    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseMovieEntities = getMovieEntitiesFromApi();

                    if (responseMovieEntities != null) {
                        emitter.onNext(
                            movieEntityJsonMapper.transformMovieEntityCollection(
                                responseMovieEntities
                            ));

                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    // TODO(mochamadiqbaldwicahyo): 2019-07-15 Fix this generic
                    //  exception
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<MovieEntity> movieEntityById(int movieId) {
        return null;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
            (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    private String getMovieEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_MOVIE_POPULAR_LIST).requestSyncCall();
    }
}
