package io.mochadwi.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.mochadwi.presentation.R;
import io.mochadwi.presentation.internal.di.components.MovieComponent;
import io.mochadwi.presentation.model.MovieModel;
import io.mochadwi.presentation.presenter.MovieListPresenter;
import io.mochadwi.presentation.view.MovieListView;
import io.mochadwi.presentation.view.adapter.MoviesAdapter;

/**
 * Fragment that shows a list of Movies.
 */
public class MovieListFragment extends BaseFragment implements MovieListView {

    @Bind(R.id.bt_retry)
    Button bt_retry;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;

    @Inject
    MovieListPresenter movieListPresenter;

    @Inject
    MoviesAdapter moviesAdapter;

    @Bind(R.id.rv_movies)
    RecyclerView rv_movies;

    private MovieListListener movieListListener;

    private MoviesAdapter.OnItemClickListener onItemClickListener =
        new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onMovieItemClicked(MovieModel movieModel) {
                if (MovieListFragment.this.movieListPresenter != null && movieModel != null) {
                    MovieListFragment.this.movieListPresenter.onMovieClicked(movieModel);
                }
            }
        };

    public MovieListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MovieListListener) {
            this.movieListListener = (MovieListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.movieListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.movieListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.movieListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_movies.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.movieListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.movieListListener = null;
    }

    /**
     * Loads all movies.
     */
    private void loadMovieList() {
        this.movieListPresenter.initialize();
    }

    private void setupRecyclerView() {
        this.moviesAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_movies.setLayoutManager(new LinearLayoutManager(context()));
        this.rv_movies.setAdapter(moviesAdapter);
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            this.moviesAdapter.setMoviesCollection(movieModelCollection);
        }
    }

    @Override
    public void viewMovie(MovieModel movieModel) {
        if (this.movieListListener != null) {
            this.movieListListener.onMovieClicked(movieModel);
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        MovieListFragment.this.loadMovieList();
    }

    /**
     * Interface for listening movie list events.
     */
    public interface MovieListListener {

        void onMovieClicked(final MovieModel movieModel);
    }
}
