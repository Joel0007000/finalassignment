package com.joel.finalassignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.joel.finalassignment.adapter.ProductAdapter
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class AddToCartTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity2::class.java)

    @Test
    fun addToCart()
    {
        runBlocking {

            var userRepo = UserRepository()
            ServiceBuilder.token = "Bearer "+userRepo.loginUser("oli","oli123456").token
        }
        Espresso.onView(withId(R.id.navigation_home))
            .perform(ViewActions.click())
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ProductAdapter.ProductViewHolder>(0,CustomAction.clickItemWithId(R.id.linear)))

        Thread.sleep(400)

        Espresso.onView(withId(R.id.btnAdd))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withText("Product Added to Cart"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}