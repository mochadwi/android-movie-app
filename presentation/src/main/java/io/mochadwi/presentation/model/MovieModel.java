package io.mochadwi.presentation.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class MovieModel {

    private final int userId;

    private String coverUrl;

    private String description;

    private String email;

    private int followers;

    private String fullName;

    public MovieModel(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
