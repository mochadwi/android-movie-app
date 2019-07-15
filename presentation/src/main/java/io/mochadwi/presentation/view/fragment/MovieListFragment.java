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

    @Bind(R.id.rv_users)
    RecyclerView rv_users;

    @Inject
    MovieListPresenter userListPresenter;

    @Inject
    MoviesAdapter usersAdapter;

    private MoviesAdapter.OnItemClickListener onItemClickListener =
        new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onMovieItemClicked(MovieModel userModel) {
                if (MovieListFragment.this.userListPresenter != null && userModel != null) {
                    MovieListFragment.this.userListPresenter.onMovieClicked(userModel);
                }
            }
        };

    private MovieListListener userListListener;

    public MovieListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MovieListListener) {
            this.userListListener = (MovieListListener) activity;
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
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.userListListener = null;
    }

    /**
     * Loads all users.
     */
    private void loadMovieList() {
        this.userListPresenter.initialize();
    }

    private void setupRecyclerView() {
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_users.setLayoutManager(new LinearLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
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
    public void renderMovieList(Collection<MovieModel> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setMoviesCollection(userModelCollection);
        }
    }

    @Override
    public void viewMovie(MovieModel userModel) {
        if (this.userListListener != null) {
            this.userListListener.onMovieClicked(userModel);
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        MovieListFragment.this.loadMovieList();
    }

    /**
     * Interface for listening user list events.
     */
    public interface MovieListListener {

        void onMovieClicked(final MovieModel userModel);
    }
}
