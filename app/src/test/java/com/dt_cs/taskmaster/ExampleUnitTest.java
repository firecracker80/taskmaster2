package com.dt_cs.taskmaster;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static androidx.core.content.MimeTypeFilter.matches;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
    @Rule
    public ActivityTestRule<MainActivity> myMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class)


    @Test
    public void clickAddTaskBtn_openAddTaskUi() throws Exception{
        onView(R.id.MainActivityAddTaskBtn)
                .perform(click());
        onView(withId(R.id.AddTaskXmlTextView))
                .check(matches(isDisplayed()));
    }
}