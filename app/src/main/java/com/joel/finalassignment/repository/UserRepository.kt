package com.joel.finalassignment.repository

import com.joel.finalassignment.api.APIRequest
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.api.UserAPI
import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.response.ImageResponse
import com.joel.finalassignment.response.ProductResponse
import com.joel.finalassignment.response.ServerResponse
import okhttp3.MultipartBody

class UserRepository():APIRequest() {
    val userService = ServiceBuilder.retroService(UserAPI::class.java)
    suspend fun registerUser(user:UserTable):ServerResponse
    {
        return apiRequest {
            userService.registerUser(user)
        }
    }

    suspend fun loginUser(username:String,password:String):LoginResponse
    {
        return apiRequest {

            userService.loginUser(username,password)
        }
    }
    suspend fun getProduct():ProductResponse{
        return apiRequest {
            userService.get()
        }
    }
    suspend fun uploadImage(id:String,body:MultipartBody.Part):ImageResponse{
        return apiRequest {
            userService.upload(id,body)
        }
    }
}