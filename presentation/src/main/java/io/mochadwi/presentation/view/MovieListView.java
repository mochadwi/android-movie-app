package io.mochadwi.presentation.view;

import java.util.Collection;

import io.mochadwi.presentation.model.MovieModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link MovieModel}.
 */
public interface MovieListView extends LoadDataView {

    /**
     * Render a movie list in the UI.
     *
     * @param movieModelCollection The collection of {@link MovieModel} that will be shown.
     */
    void renderMovieList(Collection<MovieModel> movieModelCollection);

    /**
     * View a {@link MovieModel} profile/details.
     *
     * @param movieModel The movie that will be shown.
     */
    void viewMovie(MovieModel movieModel);
}
