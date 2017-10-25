package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static com.project.demorecord.TestUtils.withRecyclerView;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void noInfoTest() {
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void onlyAgeInfoTest(){
        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void viewEmptyListTest(){
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    @Test
    public void onlyNameInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void firstInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge)).check(matches(withText("20")));
        onView(allOf(withId(R.id.buttonClear), isDisplayed())).perform(click());
    }

    @Test
    public void secondInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge)).check(matches(withText("20")));
        onView(allOf(withId(R.id.buttonClear), isDisplayed())).perform(click());
    }

    @Test
    public void thirdInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge)).check(matches(withText("80")));
        onView(allOf(withId(R.id.buttonClear), isDisplayed())).perform(click());
    }

    @Test
    public void forthInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("60"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge)).check(matches(withText("60")));
        onView(allOf(withId(R.id.buttonClear), isDisplayed())).perform(click());
    }

    @Test
    public void fifthInfoTest(){
        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("60"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("50"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge)).check(matches(withText("50")));
        onView(allOf(withId(R.id.buttonClear), isDisplayed())).perform(click());
    }

}
