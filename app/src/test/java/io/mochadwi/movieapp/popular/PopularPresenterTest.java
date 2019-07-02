package io.mochadwi.movieapp.popular;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.mochadwi.movieapp.feature.movie.popular.domain.interactor.GetPopular;
import io.mochadwi.movieapp.feature.movie.popular.ui.presenter.PopularPresenter;
import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class PopularPresenterTest {

    @Mock
    private GetPopular mockGetPopular;

    private PopularPresenter popularPresenter;

    @Before
    public void setUp() {
        popularPresenter = new PopularPresenter(mockGetPopular);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPopularPresenterLoad() {
        popularPresenter.loadMovies();

        verify(mockGetPopular).execute(any(DisposableObserver.class), isNull());
    }
}