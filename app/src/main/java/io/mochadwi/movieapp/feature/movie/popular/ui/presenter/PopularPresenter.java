package io.mochadwi.movieapp.feature.movie.popular.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import io.mochadwi.movieapp.feature.core.interactor.DefaultObserver;
import io.mochadwi.movieapp.feature.movie.popular.domain.interactor.GetPopular;
import io.mochadwi.movieapp.feature.movie.popular.domain.model.Popular;

public class PopularPresenter {

    // TODO: Adjust your needs here
    private GetPopular getPopularUseCase;

    @Inject
    public PopularPresenter(
        GetPopular getPopularUseCase) {
        this.getPopularUseCase = getPopularUseCase;
    }

    public void loadMovies() {
        getPopularUseCase.execute(new PopularListObserver(), null);
    }

    private final class PopularListObserver extends DefaultObserver<List<Popular>> {

        @Override
        public void onNext(List<Popular> populars) {
            // TODO(mochamadiqbaldwicahyo): 2019-07-01 Prepare data here
        }

        @Override
        public void onError(Throwable e) {
            // TODO(mochamadiqbaldwicahyo): 2019-07-01 View error here
        }

        @Override
        public void onComplete() {
            // TODO(mochamadiqbaldwicahyo): 2019-07-01 Stop loading or finish animation here
        }
    }
}