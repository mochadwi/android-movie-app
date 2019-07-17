package io.mochadwi.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MalformedURLException;
import java.util.List;

import io.mochadwi.data.entity.BaseEntity;
import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.exception.NetworkConnectionException;
import io.reactivex.Observable;

import static io.mochadwi.data.net.RestApiMovie.API_BASE_URL;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RetrofitMovieImpl implements RetrofitMovie {

    private final Context context;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     */
    public RetrofitMovieImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        return null;
    }

    @Override
    public Observable<BaseEntity<MovieEntity>> popularList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    RetrofitMovie service = getMovieEntitiesFromApi();

                    if (service != null) {
                        emitter.onNext(
                            service.popularList().blockingSingle()
                        );

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

    private RetrofitMovie getMovieEntitiesFromApi() throws MalformedURLException {
        return new RetrofitConnection(API_BASE_URL)
            .requestSyncCall(RetrofitMovie.class);
    }
}
