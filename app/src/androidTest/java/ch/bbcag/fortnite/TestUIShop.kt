package ch.bbcag.fortnite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestUIShop {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun TestKlickShop() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        //Espresso.onView(ViewMatchers.withId(R.id.reclyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(TestHelper().atPositionOnView(R.id.reclyclerView, 0, R.id.SkinIcon)).perform(ViewActions.click())
    }
}