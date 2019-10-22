package com.example.cystudy;

import android.content.Context;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.example.cystudy.ui.fragments.SettingsFragment;
import com.example.cystudy.ui.fragments.StudentHomeFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static junit.framework.TestCase.assertEquals;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewAdapterTest {
//
//    @Test
//    public void navigateBetweenFragments() {
//        NavController mockNavController = Mockito.mock(NavController.class);
//        FragmentScenario<StudentHomeFragment> studentHomeFragmentFragmentScenario = FragmentScenario.launchInContainer(StudentHomeFragment.class);
//
//        studentHomeFragmentFragmentScenario.onFragment(fragment ->
//                Navigation.setViewNavController(fragment.requireView(), mockNavController)
//        );
//
//        onView(ViewMatchers.withId(R.id.stats_button)).perform(ViewActions.click());
//        verify(mockNavController).navigate(R.id.action_studentHomeFragment_to_studentStatsFragment);
//    }

    private static final ArrayList<String> classesList = new ArrayList<>();

    @Mock
    Context mockContext;

    @Test
    public void testItemCount() {
        // Given a mocked Context injected into the object under test...
        classesList.add("COMS 309");
        RecyclerViewAdapter myObjectUnderTest = new RecyclerViewAdapter(mockContext, classesList);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getItemCount();

        // ...then the result should be the expected one.
        assertEquals(result, 1);
    }


}