package io.mochadwi.movieapp.feature.movie.popular.data.entity;

import io.mochadwi.movieapp.feature.movie.popular.domain.model.Popular;

// TODO: Add transformation into another entity or model here as well
public class PopularEntity {

    private Integer id;

    public Popular toPopular() {
        return new Popular(id);
    }
}
