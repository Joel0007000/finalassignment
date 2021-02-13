package com.joel.finalassignment.api

import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.response.ServerResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @POST("account/insert")
    suspend fun registerUser(@Body user: UserTable): Response<ServerResponse>

    @FormUrlEncoded
    @POST("account/login")
    suspend fun loginUser(
        @Field("acUN") username: String,
        @Field("acPassword") password: String 
    ):Response<ServerResponse>

}