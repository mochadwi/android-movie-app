package io.mochadwi.test.exception;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;

import io.mochadwi.data.exception.NetworkConnectionException;
import io.mochadwi.data.exception.UserNotFoundException;
import io.mochadwi.presentation.R;
import io.mochadwi.presentation.exception.ErrorMessageFactory;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ErrorMessageFactoryTest {

    @Test
    public void testNetworkConnectionErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_no_connection);
        String actualMessage = ErrorMessageFactory.create(getContext(),
            new NetworkConnectionException());

        assertThat(actualMessage, is(equalTo(expectedMessage)));
    }

    @Test
    public void testUserNotFoundErrorMessage() {
        String expectedMessage = getContext().getString(R.string.exception_message_user_not_found);
        String actualMessage = ErrorMessageFactory
            .create(getContext(), new UserNotFoundException());

        assertThat(actualMessage, is(equalTo(expectedMessage)));
    }
}
