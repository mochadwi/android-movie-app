package io.mochadwi.movieapp.feature.movie.popular.data.repository;

import java.util.List;

import androidx.annotation.Nullable;
import io.mochadwi.movieapp.feature.movie.popular.domain.model.Popular;
import io.mochadwi.movieapp.feature.movie.popular.domain.repository.PopularRepository;
import io.reactivex.Observable;

public class PopularRepositoryImpl implements PopularRepository {
    // TODO: Override the implementation here, that related to the framework

    @Nullable
    @Override
    public Observable<List<Popular>> getPopular() {
        return null;
    }
}
