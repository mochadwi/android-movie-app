package io.mochadwi.data.entity.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.mochadwi.data.entity.MovieEntity;
import io.mochadwi.domain.Movie;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MovieEntityDataMapperTest {

    private static final String FAKE_FULLNAME = "Tony Stark";

    private static final int FAKE_USER_ID = 123;

    private MovieEntityDataMapper movieEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        movieEntityDataMapper = new MovieEntityDataMapper();
    }

    @Test
    public void testTransformMovieEntity() {
        MovieEntity movieEntity = createFakeMovieEntity();
        Movie movie = movieEntityDataMapper.transform(movieEntity);

        assertThat(movie, is(instanceOf(Movie.class)));
        assertThat(movie.getId(), is(FAKE_USER_ID));
        assertThat(movie.getOriginalTitle(), is(FAKE_FULLNAME));
    }

    private MovieEntity createFakeMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(FAKE_USER_ID);
        movieEntity.setOriginalTitle(FAKE_FULLNAME);

        return movieEntity;
    }

    @Test
    public void testTransformMovieEntityCollection() {
        MovieEntity mockMovieEntityOne = mock(MovieEntity.class);
        MovieEntity mockMovieEntityTwo = mock(MovieEntity.class);

        List<MovieEntity> movieEntityList = new ArrayList<MovieEntity>(5);
        movieEntityList.add(mockMovieEntityOne);
        movieEntityList.add(mockMovieEntityTwo);

        Collection<Movie> movieCollection = movieEntityDataMapper.transform(movieEntityList);

        assertThat(movieCollection.toArray()[0], is(instanceOf(Movie.class)));
        assertThat(movieCollection.toArray()[1], is(instanceOf(Movie.class)));
        assertThat(movieCollection.size(), is(2));
    }
}
