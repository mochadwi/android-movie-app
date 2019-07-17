package io.mochadwi.data.net;

import android.support.annotation.Nullable;

import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link Callable} so when executed asynchronously can
 * return a value.
 */
class RetrofitConnection {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";

    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private String urlString;

    RetrofitConnection(String url) throws MalformedURLException {
        this.urlString = url;
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    <C> C requestSyncCall(Class<C> serviceClass) {
        return connectToApi(serviceClass);
    }

    private <C> C connectToApi(Class<C> serviceClass) {
        final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(this.urlString)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(this.createClient())
            .build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(15000, TimeUnit.MILLISECONDS)
            .build();
    }
}
