package com.example.myapplication;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void mainActivityTest() {
   ViewInteraction appCompatEditText = onView (withId(R.id.editText));
   appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

   ViewInteraction materialButton = onView(withId(R.id.button));
   materialButton.perform(click());

   ViewInteraction textView = onView(withId(R.id.textView3));
   textView.check(matches(withText("You shall not pass!")));
    }

    /**
    This test case inserts text missing digits to test our password checks created
     in our main activity by submitting the inserted text by clicking the button
     which should change our textview in which the test case checks if the textview
     contains the proper text meaning our test worked correctly
     */
    @Test
    public void testFindMissingDigit(){
        //finds the view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        // types in password#$*
        appCompatEditText.perform(replaceText("password#$*"));
        //finds the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //clicks the button
        materialButton.perform(click());
        //finds the text view
        ViewInteraction textView = onView(withId(R.id.textView3));
        //checks the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     This test case inserts text that complies to all the password complexity
     requirments and tests that everything is structured correctly
     in our main activity by submitting the inserted text by clicking the button
     which should change our textview in which the test case checks if the textview
     contains the proper text meaning our test worked correctly
     */
    @Test
    public void testPasswordComplex(){
        //finds the view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        // types in Password123#$*
        appCompatEditText.perform(replaceText("Password123#$*"));
        //finds the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //clicks the button
        materialButton.perform(click());
        //finds the text view
        ViewInteraction textView = onView(withId(R.id.textView3));
        //checks the text
        textView.check(matches(withText("Your password is complex enough")));
    }

    /**
     This test case inserts text missing special characters to test our password
     checks created in our main activity by submitting the inserted text by clicking the button
     which should change our textview in which the test case checks if the textview
     contains the proper text meaning our test worked correctly
     */
    @Test
    public void testFindMissingSpecial(){
        //finds the view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        // types in password123
        appCompatEditText.perform(replaceText("password123"));
        //finds the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //clicks the button
        materialButton.perform(click());
        //finds the text view
        ViewInteraction textView = onView(withId(R.id.textView3));
        //checks the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     This test case inserts text lower case characters to test our password
     checks created in our main activity by submitting the inserted text by clicking the button
     which should change our textview in which the test case checks if the textview
     contains the proper text meaning our test worked correctly
     */
    @Test
    public void testFindMissingLowerCase(){
        //finds the view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        // types in PASSWORD123#$*
        appCompatEditText.perform(replaceText(" PASSWORD123#$*"));
        //finds the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //clicks the button
        materialButton.perform(click());
        //finds the text view
        ViewInteraction textView = onView(withId(R.id.textView3));
        //checks the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     This test case inserts text upper case characters to test our password
     checks created in our main activity by submitting the inserted text by clicking the button
     which should change our textview in which the test case checks if the textview
     contains the proper text meaning our test worked correctly
     */
    @Test
    public void testFindMissingUpperCase(){
        //finds the view
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        // types in password123#$*
        appCompatEditText.perform(replaceText("password123#$*"));
        //finds the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //clicks the button
        materialButton.perform(click());
        //finds the text view
        ViewInteraction textView = onView(withId(R.id.textView3));
        //checks the text
        textView.check(matches(withText("You shall not pass!")));
    }
}
