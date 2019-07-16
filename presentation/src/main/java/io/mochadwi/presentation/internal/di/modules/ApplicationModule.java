package io.mochadwi.presentation.internal.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mochadwi.data.cache.MovieCache;
import io.mochadwi.data.cache.MovieCacheImpl;
import io.mochadwi.data.cache.UserCache;
import io.mochadwi.data.cache.UserCacheImpl;
import io.mochadwi.data.executor.JobExecutor;
import io.mochadwi.data.repository.MovieDataRepository;
import io.mochadwi.data.repository.UserDataRepository;
import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.MovieRepository;
import io.mochadwi.domain.repository.UserRepository;
import io.mochadwi.presentation.AndroidApplication;
import io.mochadwi.presentation.UIThread;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }

  @Provides
  @Singleton
  MovieCache provideMovieCache(MovieCacheImpl movieCache) {
    return movieCache;
  }

  @Provides
  @Singleton
  MovieRepository provideMovieRepository(MovieDataRepository movieDataRepository) {
    return movieDataRepository;
  }
}
