package io.mochadwi.test.view.activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Fragment;
import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import io.mochadwi.presentation.R;
import io.mochadwi.presentation.view.activity.UserListActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListActivityTest extends ActivityTestRule<UserListActivity> {

    @Rule
    public ActivityTestRule<UserListActivity> activityRule = new ActivityTestRule<>(
        UserListActivity.class
    );

  private UserListActivity userListActivity;

  public UserListActivityTest() {
    super(UserListActivity.class);
  }

    @Before
    public void setUp() throws Exception {
        this.launchActivity(createTargetIntent());
    userListActivity = getActivity();
  }

    private Intent createTargetIntent() {
        Intent intentLaunchActivity =
            UserListActivity.getCallingIntent(getInstrumentation().getTargetContext());

        return intentLaunchActivity;
  }

    @Test
    public void testContainsUserListFragment() {
        Fragment userListFragment =
            userListActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertThat(userListFragment, is(notNullValue()));
    }

    @Test
    public void testContainsProperTitle() {
        String actualTitle = this.userListActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("Users List"));
    }
}
