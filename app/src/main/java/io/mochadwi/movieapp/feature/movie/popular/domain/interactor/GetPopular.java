package io.mochadwi.movieapp.feature.movie.popular.domain.interactor;

import io.mochadwi.movieapp.feature.movie.popular.domain.repository.PopularRepository;

public class GetPopular {

    // TODO: This is an example of Use case (interactor between repo and model)
    private PopularRepository popularRepository;

    public GetPopular(PopularRepository popularRepository) {
        this.popularRepository = popularRepository;
    }
}
