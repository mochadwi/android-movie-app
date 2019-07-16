package io.mochadwi.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mochadwi.data.entity.BaseEntity;
import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.domain.Movie;

/**
 * Mapper class used to transform {@link MovieEntity} (in the data layer) to {@link Movie} in the
 * domain layer.
 */
@Singleton
public class MovieEntityDataMapper {

    @Inject
    MovieEntityDataMapper() {
    }

    /**
     * Transform a List of {@link MovieEntity} into a Collection of {@link Movie}.
     *
     * @param movieEntityCollection Object Collection to be transformed.
     * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
     */
    public List<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
        final List<Movie> movieList = new ArrayList<>(20);
        for (MovieEntity movieEntity : movieEntityCollection) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    /**
     * Transform a List of {@link MovieEntity} into a Collection of {@link Movie}.
     *
     * @param movieEntityCollection Object Collection to be transformed.
     * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
     */
    public List<Movie> transform(BaseEntity<MovieEntity> movieEntityCollection) {
        final List<Movie> movieList = new ArrayList<>(20);
        for (MovieEntity movieEntity : movieEntityCollection.getResults()) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    /**
     * Transform a {@link MovieEntity} into an {@link Movie}.
     *
     * @param movieEntity Object to be transformed.
     * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
     */
    public Movie transform(MovieEntity movieEntity) {
        Movie movie = null;
        if (movieEntity != null) {
            movie = new Movie(movieEntity.isAdult(), movieEntity.getBackdropPath(),
                movieEntity.getGenreIds(),
                movieEntity.getId(), movieEntity.getOriginalLanguage(),
                movieEntity.getOriginalTitle(),
                movieEntity.getOverview(), movieEntity.getPopularity(), movieEntity.getPosterPath(),
                movieEntity.getReleaseDate(), movieEntity.getTitle(), movieEntity.isVideo(),
                movieEntity.getVoteAverage(), movieEntity.getVoteCount());
        }
        return movie;
    }
}
