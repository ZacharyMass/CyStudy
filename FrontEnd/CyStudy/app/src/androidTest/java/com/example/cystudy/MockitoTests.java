package com.example.cystudy;

import android.content.Context;

import androidx.test.runner.AndroidJUnit4;

import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.example.cystudy.ui.fragments.StudentFragments.SettingsFragment;
import com.example.cystudy.ui.fragments.StudentFragments.StudentHomeFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MockitoTests {
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
    SettingsFragment sf = mock(SettingsFragment.class);
    LoginActivity login = mock(LoginActivity.class);
    StudentHomeFragment sh = mock(StudentHomeFragment.class);

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

    // Written by Brad
    @Test
    public void testLoginRole() {
        // Mock calling the getRole function in the LoginActivity
        login.getRole("dummyTeacher");

        // This should be null because the parameter inside is not actually instantiated until MainActivity
        // This is a necessary test to make sure we have correct role verification and the user can access their appropriate set of pages
        assertNull(login.role);
    }

    // Written by Zach M
    @Test
    public void testClearList() {
        //Ensures that pull classes is clearing the list before pulling the classes again
        sh.pullClasses();
        assertFalse(sh.unformattedStudentClasses.contains("COMS309"));
    }

    // Written by Zach M
    @Test
    public void testLogout() {
        // Given a mocked Student Settings Fragment...
        sf.logout();

        // ...then the result should be the expected one.
        assertEquals(null, MainActivity.user);
    }
}