package com.joel.finalassignment.api

import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.repository.LoginResponse
import com.joel.finalassignment.response.ImageResponse
import com.joel.finalassignment.response.ProductResponse
import com.joel.finalassignment.response.ServerResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @POST("account/insert")
    suspend fun registerUser(@Body user: UserTable): Response<ServerResponse>

    @FormUrlEncoded
    @POST("account/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<LoginResponse>

    @FormUrlEncoded
    @POST("update/details")
    suspend fun editDetails(
        @Header("Authorization") token:String,
        @Field("name") fn:String,

        @Field("email") em:String,
        @Field("username") un:String,
        @Field("address") ad:String,
    ):Response<LoginResponse>

    @Multipart
    @PUT("change/profilePicture")
    suspend fun changeImage(
        @Header("Authorization") token:String,
        @Part profileImg: MultipartBody.Part
    ):Response<LoginResponse>


}