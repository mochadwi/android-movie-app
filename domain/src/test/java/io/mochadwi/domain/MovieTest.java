package io.mochadwi.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

    private static final int FAKE_USER_ID = 8;

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie(FAKE_USER_ID);
    }

    @Test
    public void testMovieConstructorHappyCase() {
        final int movieId = movie.getMovieId();

        assertThat(movieId).isEqualTo(FAKE_USER_ID);
    }
}
