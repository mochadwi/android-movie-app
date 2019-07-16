package io.mochadwi.presentation.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

import lombok.Data;

/**
 * Class that represents a movie in the presentation layer.
 */
@Data
@Parcel(Parcel.Serialization.FIELD)
public class MovieModel {

    private boolean adult;

    private String backdropPath;

    private List<Integer> genreIds;

    private int id;

    private String originalLanguage;

    private String originalTitle;

    private String overview;

    private double popularity;

    private String posterPath;

    private String releaseDate;

    private String title;

    private boolean video;

    private double voteAverage;

    private int voteCount;

    @ParcelConstructor
    public MovieModel(boolean adult, String backdropPath, List<Integer> genreIds, int id,
        String originalLanguage, String originalTitle, String overview, double popularity,
        String posterPath, String releaseDate, String title, boolean video, double voteAverage,
        int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}
