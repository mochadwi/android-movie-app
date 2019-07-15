package io.mochadwi.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.MovieRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetMovieListTest {

    private GetMovieList getMovieList;

    @Mock
    private MovieRepository mockMovieRepository;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Before
    public void setUp() {
        getMovieList = new GetMovieList(mockMovieRepository, mockThreadExecutor,
            mockPostExecutionThread);
    }

    @Test
    public void testGetMovieListUseCaseObservableHappyCase() {
        getMovieList.buildUseCaseObservable(null);

        verify(mockMovieRepository).movies();
        verifyNoMoreInteractions(mockMovieRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
