package io.mochadwi.presentation.internal.di.components;

import io.mochadwi.presentation.internal.di.PerActivity;
import io.mochadwi.presentation.internal.di.modules.ActivityModule;
import io.mochadwi.presentation.internal.di.modules.UserModule;
import io.mochadwi.presentation.view.fragment.UserDetailsFragment;
import io.mochadwi.presentation.view.fragment.UserListFragment;
import dagger.Component;

/**
 * A scope {@link io.mochadwi.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(UserListFragment userListFragment);
  void inject(UserDetailsFragment userDetailsFragment);
}
