package com.joel.clothingwearos.api

import com.joel.clothingwearos.response.BookingResponse
import com.joel.clothingwearos.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface WearAPI {
    @FormUrlEncoded
    @POST("account/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @GET("retrieve/myBookings")
    suspend fun retrieveBooking(
        @Header("Authorization") token:String
    ): Response<BookingResponse>
}