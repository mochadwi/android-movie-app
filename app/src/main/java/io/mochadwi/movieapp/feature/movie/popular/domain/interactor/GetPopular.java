package io.mochadwi.movieapp.feature.movie.popular.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.mochadwi.movieapp.feature.core.executor.PostExecutionThread;
import io.mochadwi.movieapp.feature.core.executor.ThreadExecutor;
import io.mochadwi.movieapp.feature.core.interactor.UseCase;
import io.mochadwi.movieapp.feature.movie.popular.domain.model.Popular;
import io.mochadwi.movieapp.feature.movie.popular.domain.repository.PopularRepository;
import io.reactivex.Observable;

public class GetPopular extends UseCase<List<Popular>, Void> {

    // TODO: This is an example of Use case (interactor between repo and model)
    private PopularRepository popularRepository;

    @Inject
    public GetPopular(ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread,
        PopularRepository popularRepository) {
        super(threadExecutor, postExecutionThread);
        this.popularRepository = popularRepository;
    }

    @Override
    public Observable<List<Popular>> buildUseCaseObservable(Void aVoid) {
        return popularRepository.getPopular();
    }
}
