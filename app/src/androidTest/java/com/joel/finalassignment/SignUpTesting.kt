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
class SignUpTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(SignUpActivity::class.java)
    @Test
    fun checkRegisterUI()
    {
        Espresso.onView(withId(R.id.fullname))
            .perform(ViewActions.typeText("joel"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)
        Espresso.onView(withId(R.id.username))
            .perform(ViewActions.typeText("joel123456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)
        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.typeText("joel@gmail.com"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)

        Espresso.onView(withId(R.id.phone))
            .perform(ViewActions.typeText("9811111111"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)

        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText("123456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(200)

        Espresso.onView(withId(R.id.btn_SignUpGO))
            .perform(ViewActions.click())
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}