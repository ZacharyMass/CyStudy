package com.example.cystudy;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.example.cystudy.ui.fragments.StudentFragments.StudentFlashcardsFragment;
import com.example.cystudy.ui.fragments.StudentFragments.StudentHomeFragment;
import com.example.cystudy.ui.fragments.StudentFragments.StudentSettingsFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static junit.framework.TestCase.assertEquals;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewAdapterTest {
    // Written by Zach M
//    @Test
//    public void navigateBetweenFragments() {
//        NavController mockNavController = Mockito.mock(NavController.class);
//        FragmentScenario<StudentFlashcardsFragment> studentFlashcardsFragmentFragmentScenario = FragmentScenario.launchInContainer(StudentFlashcardsFragment.class);
//
//        studentFlashcardsFragmentFragmentScenario.onFragment(fragment ->
//                Navigation.setViewNavController(fragment.requireView(), mockNavController)
//        );
//
//        onView(ViewMatchers.withId(R.id.GameButton)).perform(ViewActions.click());
//        verify(mockNavController).navigate(R.id.action_classFragment_to_gameFragment);
//    }

    private static final ArrayList<String> classesList = new ArrayList<>();

    @Mock
    Context mockContext = mock(Context.class);
    StudentSettingsFragment sf = mock(StudentSettingsFragment.class);

    // Written by Zach M
    @Test
    public void testItemCount() {
        doReturn("Hello").when(mockContext).toString();

        // Given a mocked Context injected into the object under test...
        classesList.add("COMS 309");
        RecyclerViewAdapter myObjectUnderTest = new RecyclerViewAdapter(mockContext, classesList);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getItemCount();

        // ...then the result should be the expected one.
        assertEquals(result, 1);
    }

    // Written by Zach M
    @Test
    public void testLogout() {
        // Given a mocked Student Settings Fragment...
        sf.logout();

        // ...then the result should be the expected one.
        assertEquals(MainActivity.user, null);
    }
}