package com.joel.finalassignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.joel.finalassignment.adapter.BookingAdapter
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class DeleteCartTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity2::class.java)

    @Test
    fun deleteCartUI()
    {
        runBlocking {

            var userRepo = UserRepository()
            ServiceBuilder.token = "Bearer "+userRepo.loginUser("oli","oli123456").token
        }

        Espresso.onView(ViewMatchers.withId(R.id.navigation_dashboard))
            .perform(ViewActions.click())

        Thread.sleep(400)

        Espresso.onView(ViewMatchers.withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookingAdapter.BookingViewHolder>(0,CustomAction.clickItemWithId(R.id.cbCheck)))

        Thread.sleep(400)

        Espresso.onView(ViewMatchers.withText("Delete"))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withText("Continue Shopping"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}