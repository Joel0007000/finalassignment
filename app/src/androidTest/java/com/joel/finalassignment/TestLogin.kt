package com.joel.finalassignment

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class TestLogin {

    @get: Rule
    val testRule=ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginUI(){
        onView(withId(R.id.username)).perform(typeText("abc"))
        onView(withId(R.id.password)).perform(typeText("abc12345"))

        closeSoftKeyboard()

        onView(withId(R.id.btn_loginGo)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

    }
}