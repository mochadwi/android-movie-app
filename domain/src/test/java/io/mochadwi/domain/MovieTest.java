package io.mochadwi.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

    private static final int FAKE_MOVIE_ID = 8;

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie(FAKE_MOVIE_ID);
    }

    @Test
    public void testMovieConstructorHappyCase() {
        final int movieId = movie.getId();

        assertThat(movieId).isEqualTo(FAKE_MOVIE_ID);
    }
}
