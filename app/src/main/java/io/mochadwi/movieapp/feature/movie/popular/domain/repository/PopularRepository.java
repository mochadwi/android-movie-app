package io.mochadwi.movieapp.feature.movie.popular.domain.repository;

import java.util.List;

import androidx.annotation.Nullable;
import io.mochadwi.movieapp.feature.movie.popular.domain.model.Popular;
import io.reactivex.Observable;

public interface PopularRepository {
    // TODO: Add method/action/endpoint here. Remember this is the interface, that will be used
    //  across data and ui layer

    @Nullable
    Observable<List<Popular>> getPopular();
}
