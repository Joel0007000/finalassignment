package com.joel.finalassignment

import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.entity.Booking
import com.joel.finalassignment.entity.Product
import com.joel.finalassignment.entity.User
import com.joel.finalassignment.repository.BookingRepository
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {
    @Test
    fun loginTesting() {
        runBlocking {
            val expectedResult = true
            val repository = UserRepository()
            var response = repository.loginUser("oli", "oli123456")
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun signUpTesting() {
        runBlocking {
            val expectedResult = true
            val repository = UserRepository()
            var user = UserTable(fullname = "test test", email = "test@gmail.com", username = "test1234",
                phone = "9802456532", password = "123456")
            var response = repository.registerUser(user)
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun addToCartTesting() {
        runBlocking {
            val expectedResult = true
            val repo = UserRepository()
            ServiceBuilder.token = "Bearer " + repo.loginUser("oli", "oli123456").token
            var repository = BookingRepository()
            var booking = Booking(
                product_id = Product(_id = "606ca27935699136500d908a"),
                quantity = 1,
                price = 750
            )
            var response = repository.bookFurniture(booking)
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)

        }
    }
    @Test
    fun cartTesting() {
        runBlocking {
            val expectedResult = true
            val repo = UserRepository()
            ServiceBuilder.token = "Bearer " + repo.loginUser("oli", "oli123456").token
            var repository = BookingRepository()
            var response=repository.retrieveBooking()
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun deleteItemsTesting(){
        runBlocking {
            var expectedResult=true
            val repo = UserRepository()
            ServiceBuilder.token="Bearer " + repo.loginUser("oli","oli123456").token
            var repository=BookingRepository()
            var response=repository.deleteBooking("6069b3f6821fd01d00aedad9")
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun loginTestings() {
        runBlocking {
            val expectedResult = false
            val repository = UserRepository()
            var response = repository.loginUser("oli", "oli123456")
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun signUpTestings() {
        runBlocking {
            val expectedResult = true
            val repository = UserRepository()
            var user = UserTable(fullname = "test test", email = "test@gmail.com", username = "test1234",
                phone = "9802456532", password = "123456")
            var response = repository.registerUser(user)
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun addToCartTestings() {
        runBlocking {
            val expectedResult = true
            val repo = UserRepository()
            ServiceBuilder.token = "Bearer " + repo.loginUser("oli", "oli123456").token
            var repository = BookingRepository()
            var booking = Booking(
                product_id = Product(_id = "606ca27935699136500d908a"),
                quantity = 1,
                price = 750
            )
            var response = repository.bookFurniture(booking)
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)

        }
    }
    @Test
    fun cartTestings() {
        runBlocking {
            val expectedResult = false
            val repo = UserRepository()
            ServiceBuilder.token = "Bearer " + repo.loginUser("oli", "oli123456").token
            var repository = BookingRepository()
            var response=repository.retrieveBooking()
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun deleteItemsTestings(){
        runBlocking {
            var expectedResult=true
            val repo = UserRepository()
            ServiceBuilder.token="Bearer " + repo.loginUser("oli","oli123456").token
            var repository=BookingRepository()
            var response=repository.deleteBooking("6069b3f6821fd01d00aedad9")
            var actualResult = response.success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }


}