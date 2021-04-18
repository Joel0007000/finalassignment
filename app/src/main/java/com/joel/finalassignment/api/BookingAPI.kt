package com.joel.finalassignment.api

import com.joel.finalassignment.entity.Booking
import com.joel.finalassignment.response.BookingResponse
import retrofit2.Response
import retrofit2.http.*

interface BookingAPI {
    @POST("book/cloth")
    suspend fun bookProduct(
            @Header("Authorization") token:String,
            @Body record: Booking
    ): Response<BookingResponse>


    @GET("retrieve/myBookings")
    suspend fun retrieveBooking(
            @Header("Authorization") token:String
    ): Response<BookingResponse>

    @FormUrlEncoded
    @POST("update/booking")
    suspend fun updateBooking(
            @Header("Authorization") token:String,
            @Field("pid") id:String,
            @Field("qty") qty:Int
    ): Response<BookingResponse>

    @FormUrlEncoded
    @POST("delete/booking")
    suspend fun deleteBooking(
            @Header("Authorization") token:String,
            @Field("pid") id:String
    ): Response<BookingResponse>
}