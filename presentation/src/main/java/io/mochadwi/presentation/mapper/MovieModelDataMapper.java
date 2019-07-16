package io.mochadwi.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import io.mochadwi.domain.Movie;
import io.mochadwi.presentation.internal.di.PerActivity;
import io.mochadwi.presentation.model.MovieModel;

/**
 * Mapper class used to transform {@link Movie} (in the domain layer) to {@link MovieModel} in the
 * presentation layer.
 */
@PerActivity
public class MovieModelDataMapper {

    @Inject
    public MovieModelDataMapper() {
    }

    /**
     * Transform a Collection of {@link Movie} into a Collection of {@link MovieModel}.
     *
     * @param moviesCollection Objects to be transformed.
     * @return List of {@link MovieModel}.
     */
    public Collection<MovieModel> transform(Collection<Movie> moviesCollection) {
        Collection<MovieModel> movieModelsCollection;

        if (moviesCollection != null && !moviesCollection.isEmpty()) {
            movieModelsCollection = new ArrayList<>();
            for (Movie movie : moviesCollection) {
                movieModelsCollection.add(transform(movie));
            }
        } else {
            movieModelsCollection = Collections.emptyList();
        }

        return movieModelsCollection;
    }

    /**
     * Transform a {@link Movie} into an {@link MovieModel}.
     *
     * @param movie Object to be transformed.
     * @return {@link MovieModel}.
     */
    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new MovieModel(movie.isAdult(), movie.getBackdropPath(),
            movie.getGenreIds(),
            movie.getId(), movie.getOriginalLanguage(),
            movie.getOriginalTitle(),
            movie.getOverview(), movie.getPopularity(), movie.getPosterPath(),
            movie.getReleaseDate(), movie.getTitle(), movie.isVideo(),
            movie.getVoteAverage(), movie.getVoteCount());
    }
}
