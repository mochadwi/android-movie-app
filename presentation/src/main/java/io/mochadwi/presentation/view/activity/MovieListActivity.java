package io.mochadwi.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import io.mochadwi.presentation.R;
import io.mochadwi.presentation.internal.di.HasComponent;
import io.mochadwi.presentation.internal.di.components.DaggerMovieComponent;
import io.mochadwi.presentation.internal.di.components.MovieComponent;
import io.mochadwi.presentation.model.MovieModel;
import io.mochadwi.presentation.view.fragment.MovieListFragment;

/**
 * Activity that shows a list of Movies.
 */
public class MovieListActivity extends BaseActivity implements HasComponent<MovieComponent>,
    MovieListFragment.MovieListListener {

    private MovieComponent movieComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MovieListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new MovieListFragment());
        }
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }

    @Override
    public void onMovieClicked(MovieModel movieModel) {
        this.navigator.navigateToUserDetails(this, movieModel.getId());
    }
}
