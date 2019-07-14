package io.mochadwi.presentation.internal.di.components;

import android.content.Context;
import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.UserRepository;
import io.mochadwi.presentation.internal.di.modules.ApplicationModule;
import io.mochadwi.presentation.view.activity.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  UserRepository userRepository();
}
