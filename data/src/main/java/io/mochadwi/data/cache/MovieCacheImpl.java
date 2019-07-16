package io.mochadwi.data.cache;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mochadwi.data.cache.serializer.Serializer;
import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.data.exception.MovieNotFoundException;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.reactivex.Observable;

/**
 * {@link MovieCache} implementation.
 */
@Singleton
public class MovieCacheImpl implements MovieCache {

    private static final String DEFAULT_FILE_NAME = "movie_";

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private static final String SETTINGS_FILE_NAME = "io.mochadwi.SETTINGS";

    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private final File cacheDir;

    private final Context context;

    private final FileManager fileManager;

    private final Serializer serializer;

    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link MovieCacheImpl}.
     *
     * @param context     A
     * @param serializer  {@link Serializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    MovieCacheImpl(Context context, Serializer serializer,
        FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<MovieEntity> get(final int movieId) {
        return Observable.create(emitter -> {
            final File movieEntityFile = MovieCacheImpl.this.buildFile(movieId);
            final String fileContent = MovieCacheImpl.this.fileManager
                .readFileContent(movieEntityFile);
            final MovieEntity movieEntity =
                MovieCacheImpl.this.serializer.deserialize(fileContent, MovieEntity.class);

            if (movieEntity != null) {
                emitter.onNext(movieEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new MovieNotFoundException());
            }
        });
    }

    @Override
    public void put(MovieEntity movieEntity) {
        if (movieEntity != null) {
            final File movieEntityFile = this.buildFile(movieEntity.getId());
            if (!isCached(movieEntity.getId())) {
                final String jsonString = this.serializer.serialize(movieEntity, MovieEntity.class);
                this.executeAsynchronously(
                    new CacheWriter(this.fileManager, movieEntityFile, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(int movieId) {
        final File movieEntityFile = this.buildFile(movieId);
        return this.fileManager.exists(movieEntityFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
            SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
            SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param movieId The id movie to build the file.
     * @return A valid file.
     */
    private File buildFile(int movieId) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(movieId);

        return new File(fileNameBuilder.toString());
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {

        private final String fileContent;

        private final FileManager fileManager;

        private final File fileToWrite;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {

        private final File cacheDir;

        private final FileManager fileManager;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
