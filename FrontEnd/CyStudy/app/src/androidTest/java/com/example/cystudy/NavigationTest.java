package com.example.cystudy;

import android.view.View;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import com.example.cystudy.ui.fragments.StudentHomeFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static androidx.test.espresso.Espresso.onView;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Test
    public void navigateToRegister() {
        final NavController mockNavController = Mockito.mock(NavController.class);
        FragmentScenario<StudentHomeFragment> studentHomeFragmentFragmentScenario = FragmentScenario.launchInContainer(StudentHomeFragment.class);

        studentHomeFragmentFragmentScenario.onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), mockNavController)
        );

        onView(ViewMatchers.withId(R.id.action_settings)).perform(ViewActions.click());
        verify(mockNavController).navigate(R.id.action_studentHomeFragment_to_settingsFragment);
    }
}