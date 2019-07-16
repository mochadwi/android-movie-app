package io.mochadwi.data.entity.mapper;

import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import io.mochadwi.data.entity.MovieEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieEntityJsonMapperTest {

    private static final String JSON_RESPONSE_MOVIE_COLLECTION = "[\n" +
        "    {\n" +
        "      \"poster_path\": \"/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg\",\n" +
        "      \"adult\": false,\n" +
        "      \"overview\": \"From DC Comics comes the Suicide Squad, an antihero team of " +
        "incarcerated supervillains who act as deniable assets for the United States government, " +
        "undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\n" +
        "      \"release_date\": \"2016-08-03\",\n" +
        "      \"genre_ids\": [\n" +
        "        14,\n" +
        "        28,\n" +
        "        80\n" +
        "      ],\n" +
        "      \"id\": 297761,\n" +
        "      \"original_title\": \"Suicide Squad\",\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"title\": \"Suicide Squad\",\n" +
        "      \"backdrop_path\": \"/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg\",\n" +
        "      \"popularity\": 48.261451,\n" +
        "      \"vote_count\": 1466,\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 5.91\n" +
        "    },\n" +
        "    {\n" +
        "      \"poster_path\": \"/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg\",\n" +
        "      \"adult\": false,\n" +
        "      \"overview\": \"The most dangerous former operative of the CIA is drawn out of " +
        "hiding to uncover hidden truths about his past.\",\n" +
        "      \"release_date\": \"2016-07-27\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 324668,\n" +
        "      \"original_title\": \"Jason Bourne\",\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"title\": \"Jason Bourne\",\n" +
        "      \"backdrop_path\": \"/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg\",\n" +
        "      \"popularity\": 30.690177,\n" +
        "      \"vote_count\": 649,\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 5.25\n" +
        "    }]";

    private static final String JSON_RESPONSE_MOVIE_DETAILS = "{\n" +
        "  \"adult\": false,\n" +
        "  \"backdrop_path\": \"/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg\",\n" +
        "  \"belongs_to_collection\": null,\n" +
        "  \"budget\": 63000000,\n" +
        "  \"genres\": [\n" +
        "    {\n" +
        "      \"id\": 18,\n" +
        "      \"name\": \"Drama\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"homepage\": \"\",\n" +
        "  \"id\": 550,\n" +
        "  \"imdb_id\": \"tt0137523\",\n" +
        "  \"original_language\": \"en\",\n" +
        "  \"original_title\": \"Fight Club\",\n" +
        "  \"overview\": \"A ticking-time-bomb insomniac and a slippery soap salesman channel " +
        "primal male aggression into a shocking new form of therapy. Their concept catches on, " +
        "with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in " +
        "the way and ignites an out-of-control spiral toward oblivion.\",\n" +
        "  \"popularity\": 0.5,\n" +
        "  \"poster_path\": \"/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg\",\n" +
        "  \"production_companies\": [\n" +
        "    {\n" +
        "      \"id\": 508,\n" +
        "      \"logo_path\": \"/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png\",\n" +
        "      \"name\": \"Regency Enterprises\",\n" +
        "      \"origin_country\": \"US\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 711,\n" +
        "      \"logo_path\": null,\n" +
        "      \"name\": \"Fox 2000 Pictures\",\n" +
        "      \"origin_country\": \"\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 20555,\n" +
        "      \"logo_path\": null,\n" +
        "      \"name\": \"Taurus Film\",\n" +
        "      \"origin_country\": \"\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 54050,\n" +
        "      \"logo_path\": null,\n" +
        "      \"name\": \"Linson Films\",\n" +
        "      \"origin_country\": \"\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 54051,\n" +
        "      \"logo_path\": null,\n" +
        "      \"name\": \"Atman Entertainment\",\n" +
        "      \"origin_country\": \"\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 54052,\n" +
        "      \"logo_path\": null,\n" +
        "      \"name\": \"Knickerbocker Films\",\n" +
        "      \"origin_country\": \"\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 25,\n" +
        "      \"logo_path\": \"/qZCc1lty5FzX30aOCVRBLzaVmcp.png\",\n" +
        "      \"name\": \"20th Century Fox\",\n" +
        "      \"origin_country\": \"US\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"production_countries\": [\n" +
        "    {\n" +
        "      \"iso_3166_1\": \"US\",\n" +
        "      \"name\": \"United States of America\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"release_date\": \"1999-10-12\",\n" +
        "  \"revenue\": 100853753,\n" +
        "  \"runtime\": 139,\n" +
        "  \"spoken_languages\": [\n" +
        "    {\n" +
        "      \"iso_639_1\": \"en\",\n" +
        "      \"name\": \"English\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"status\": \"Released\",\n" +
        "  \"tagline\": \"How much can you know about yourself if you've never been in a " +
        "fight?\",\n" +
        "  \"title\": \"Fight Club\",\n" +
        "  \"video\": false,\n" +
        "  \"vote_average\": 7.8,\n" +
        "  \"vote_count\": 3439\n" +
        "}";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MovieEntityJsonMapper movieEntityJsonMapper;

    @Before
    public void setUp() {
        movieEntityJsonMapper = new MovieEntityJsonMapper();
    }

    @Test
    public void testTransformMovieEntityHappyCase() {
        MovieEntity movieEntity = movieEntityJsonMapper
            .transformMovieEntity(JSON_RESPONSE_MOVIE_DETAILS);

        assertThat(movieEntity.getId(), is(550));
        assertThat(movieEntity.getOriginalTitle(), is(equalTo("Fight Club")));
        assertThat(movieEntity.getPosterPath(), is(equalTo("/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg")));
    }

    @Test
    public void testTransformMovieEntityCollectionHappyCase() {
        Collection<MovieEntity> movieEntityCollection =
            movieEntityJsonMapper.transformMovieEntityCollection(
                JSON_RESPONSE_MOVIE_COLLECTION);

        assertThat(((MovieEntity) movieEntityCollection.toArray()[0]).getId(), is(297761));
        assertThat(((MovieEntity) movieEntityCollection.toArray()[1]).getId(), is(324668));
        assertThat(movieEntityCollection.size(), is(2));
    }

    @Test
    public void testTransformMovieEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        movieEntityJsonMapper.transformMovieEntity("ironman");
    }

    @Test
    public void testTransformMovieEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        movieEntityJsonMapper.transformMovieEntityCollection("Tony Stark");
    }
}
