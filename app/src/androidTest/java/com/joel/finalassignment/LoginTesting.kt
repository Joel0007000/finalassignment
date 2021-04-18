package com.joel.finalassignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun checkLoginUI()
    {
        Espresso.onView(withId(R.id.username))
            .perform(ViewActions.typeText("oli"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText("oli123456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)
        Espresso.onView(withId(R.id.btn_loginGo))
            .perform(ViewActions.click())
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}