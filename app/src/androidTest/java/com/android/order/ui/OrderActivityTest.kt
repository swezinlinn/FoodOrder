package com.android.order.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.android.order.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class OrderActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(OrderActivity::class.java)

    @Test
    fun orderActivityTest2() {
        val appCompatImageView = onView(
allOf(withId(R.id.imgIngredient),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
0),
0),
isDisplayed()))
        appCompatImageView.perform(click())
        
        val tabView = onView(
allOf(withContentDescription("Burgers"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
1),
isDisplayed()))
        tabView.perform(click())
        
        val tabView2 = onView(
allOf(withContentDescription("Desserts & Shakes"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
2),
isDisplayed()))
        tabView2.perform(click())
        
        val tabView3 = onView(
allOf(withContentDescription("Dinner"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
3),
isDisplayed()))
        tabView3.perform(click())
        
        val tabView4 = onView(
allOf(withContentDescription("Breakfast"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
4),
isDisplayed()))
        tabView4.perform(click())
        
        val tabView5 = onView(
allOf(withContentDescription("Lunch"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
5),
isDisplayed()))
        tabView5.perform(click())
        
        val tabView6 = onView(
allOf(withContentDescription("Drinks"),
childAtPosition(
childAtPosition(
withId(R.id.tabs),
0),
0),
isDisplayed()))
        tabView6.perform(click())
        
        val appCompatImageView2 = onView(
allOf(withId(R.id.search_button), withContentDescription("Search"),
childAtPosition(
allOf(withId(R.id.search_bar),
childAtPosition(
withId(R.id.search),
0)),
1),
isDisplayed()))
        appCompatImageView2.perform(click())
        
        val searchAutoComplete = onView(
allOf(withId(R.id.search_src_text),
childAtPosition(
allOf(withId(R.id.search_plate),
childAtPosition(
withId(R.id.search_edit_frame),
1)),
0),
isDisplayed()))
        searchAutoComplete.perform(replaceText("Bubble"), closeSoftKeyboard())
        
        val appCompatImageButton = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
0),
isDisplayed()))
        appCompatImageButton.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
