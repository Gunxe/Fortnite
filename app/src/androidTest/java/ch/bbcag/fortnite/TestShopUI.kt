package ch.bbcag.fortnite

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestShopUI : TestCase(){
    @Rule
    public





    @Test
    fun testClickOnSkin(){
        //onView(withId(R.id.reclyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<>(0, click()))
        onData(anything()).inAdapterView(withId(R.id.reclyclerView)).atPosition(0).perform(click())
    }
}