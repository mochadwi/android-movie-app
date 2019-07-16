package io.mochadwi.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.mochadwi.domain.Movie;
import io.mochadwi.domain.exception.DefaultErrorBundle;
import io.mochadwi.domain.exception.ErrorBundle;
import io.mochadwi.domain.interactor.DefaultObserver;
import io.mochadwi.domain.interactor.GetMovieList;
import io.mochadwi.domain.interactor.GetPopularList;
import io.mochadwi.presentation.exception.ErrorMessageFactory;
import io.mochadwi.presentation.internal.di.PerActivity;
import io.mochadwi.presentation.mapper.MovieModelDataMapper;
import io.mochadwi.presentation.model.MovieModel;
import io.mochadwi.presentation.view.MovieListView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MovieListPresenter implements Presenter {

    private final GetMovieList getMovieListUseCase;

    private final GetPopularList getPopularListListUseCase;

    private final MovieModelDataMapper movieModelDataMapper;

    private MovieListView viewListView;

    @Inject
    public MovieListPresenter(GetMovieList getMovieListMovieCase,
        GetPopularList getPopularListListUseCase,
        MovieModelDataMapper movieModelDataMapper) {
        this.getMovieListUseCase = getMovieListMovieCase;
        this.getPopularListListUseCase = getPopularListListUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getMovieListUseCase.dispose();
        this.getPopularListListUseCase.dispose();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the movie list.
     */
    public void initialize() {
        this.loadMovieList();
    }

    /**
     * Loads all movies.
     */
    private void loadMovieList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPopularList();
    }


    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void getPopularList() {
        this.getPopularListListUseCase.execute(new MovieListObserver(), null);
    }

    private void getMovieList() {
        this.getMovieListUseCase.execute(new MovieListObserver(), null);
    }

    public void onMovieClicked(MovieModel movieModel) {
        this.viewListView.viewMovie(movieModel);
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
            errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showMoviesCollectionInView(Collection<Movie> moviesCollection) {
        final Collection<MovieModel> movieModelsCollection =
            this.movieModelDataMapper.transform(moviesCollection);
        this.viewListView.renderMovieList(movieModelsCollection);
    }

    private final class MovieListObserver extends DefaultObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            MovieListPresenter.this.showMoviesCollectionInView(movies);
        }

        @Override
        public void onComplete() {
            MovieListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MovieListPresenter.this.hideViewLoading();
            MovieListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MovieListPresenter.this.showViewRetry();
        }
    }
}
