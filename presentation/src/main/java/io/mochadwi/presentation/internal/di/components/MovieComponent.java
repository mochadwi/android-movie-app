package io.mochadwi.presentation.internal.di.components;

import dagger.Component;
import io.mochadwi.presentation.internal.di.PerActivity;
import io.mochadwi.presentation.internal.di.modules.ActivityModule;
import io.mochadwi.presentation.internal.di.modules.MovieModule;
import io.mochadwi.presentation.view.fragment.MovieListFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
    modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent extends ActivityComponent {

    void inject(MovieListFragment movieListFragment);
}
