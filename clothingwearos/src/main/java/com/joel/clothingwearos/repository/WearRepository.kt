package com.joel.clothingwearos.repository

import com.joel.clothingwearos.api.ApiRequest
import com.joel.clothingwearos.api.ServiceBuilder
import com.joel.clothingwearos.api.WearAPI
import com.joel.clothingwearos.response.BookingResponse
import com.joel.clothingwearos.response.LoginResponse

class WearRepository():ApiRequest() {
    val userService = ServiceBuilder.retroService(WearAPI::class.java)

    suspend fun loginUser(username:String,password:String): LoginResponse
    {
        return apiRequest {

            userService.loginUser(username,password)
        }
    }

    suspend fun retrieveBooking(): BookingResponse
    {
        return apiRequest {
            userService.retrieveBooking(ServiceBuilder.token!!)
        }
    }
}