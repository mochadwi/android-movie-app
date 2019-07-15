package io.mochadwi.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Movie Entity used in the data layer.
 */
public class MovieEntity {

    @SerializedName("cover_url")
    private String coverUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("email")
    private String email;

    @SerializedName("followers")
    private int followers;

    @SerializedName("full_name")
    private String fullname;

    @SerializedName("id")
    private int movieId;

    public MovieEntity() {
        //empty
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDescription() {
        return description;
    }

    public int getFollowers() {
        return followers;
    }

    public String getEmail() {
        return email;
    }
}
