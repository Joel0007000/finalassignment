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
    @POST("/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<LoginResponse>

    @GET("/product/show")
    suspend fun get():Response<ProductResponse>

@Multipart
    @PUT("/user/photo/{id}")
    suspend fun upload(
        @Path("id") id:String,
        @Part body: MultipartBody.Part
    ):Response<ImageResponse>
}