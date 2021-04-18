package com.joel.finalassignment.api

import com.joel.finalassignment.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("product/showAll")
    suspend fun getProduct(): Response<ProductResponse>

}