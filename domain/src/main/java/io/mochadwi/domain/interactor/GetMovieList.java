package io.mochadwi.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.mochadwi.domain.Movie;
import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.MovieRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Movie}.
 */
public class GetMovieList extends UseCase<List<Movie>, Void> {

    private final MovieRepository movieRepository;

    @Inject
    GetMovieList(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void unused) {
        return this.movieRepository.movies();
    }
}
