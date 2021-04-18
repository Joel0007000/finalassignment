package com.joel.finalassignment.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
     val BASE_URL = "http://10.0.2.2:90/"
//     val BASE_URL = "http://localhost:90/"
    var token:String?=null

    val okhttp = OkHttpClient.Builder()
    val retrofitBuild = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp.build())
    var retrofitService = retrofitBuild.build()
    fun <T> retroService(service:Class<T>):T
    {
        return retrofitService.create(service)
    }

    fun loadImgPath(): String {
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0] + "/" + arr[1] + arr[2] + "/"
    }
}