package io.mochadwi.presentation.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

/**
 * Class that represents a movie in the presentation layer.
 */
@Data
@Parcel(Parcel.Serialization.BEAN)
public class MovieModel {

    private final int movieId;

    private String coverUrl;

    private String description;

    private String email;

    private int followers;

    private String fullName;

    @ParcelConstructor
    public MovieModel(int movieId) {
        this.movieId = movieId;
    }
}
