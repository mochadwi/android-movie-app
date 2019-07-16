package io.mochadwi.test.view.activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Fragment;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import io.mochadwi.presentation.R;
import io.mochadwi.presentation.view.activity.UserDetailsActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class UserDetailsActivityTest extends ActivityTestRule<UserDetailsActivity> {

  private static final int FAKE_USER_ID = 10;

    @Rule
    public ActivityTestRule<UserDetailsActivity> activityRule = new ActivityTestRule<>(
        UserDetailsActivity.class
    );

  private UserDetailsActivity userDetailsActivity;
  public UserDetailsActivityTest() {
    super(UserDetailsActivity.class);
  }

    @Before
    public void setUp() throws Exception {
        this.launchActivity(createTargetIntent());
    this.userDetailsActivity = getActivity();
  }

    private Intent createTargetIntent() {
        Intent intentLaunchActivity =
            UserDetailsActivity
                .getCallingIntent(getInstrumentation().getTargetContext(), FAKE_USER_ID);

        return intentLaunchActivity;
  }

    @Test
    public void testContainsUserDetailsFragment() {
        Fragment userDetailsFragment =
            userDetailsActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertThat(userDetailsFragment, is(notNullValue()));
    }

    @Test
    public void testContainsProperTitle() {
        String actualTitle = this.userDetailsActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("User Details"));
    }

    @Test
    public void testLoadUserHappyCaseViews() {
        onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_fullname)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_email)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadUserHappyCaseData() {
        onView(withId(R.id.tv_fullname)).check(matches(withText("John Sanchez")));
        onView(withId(R.id.tv_email)).check(matches(withText("dmedina@katz.edu")));
        onView(withId(R.id.tv_followers)).check(matches(withText("4523")));
    }
}
