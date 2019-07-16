package io.mochadwi.presentation.model;

import lombok.Data;

/**
 * Class that represents a user in the presentation layer.
 */
@Data
public class MovieModel {

    private final int userId;

    private String coverUrl;

    private String description;

    private String email;

    private int followers;

    private String fullName;
}
