package com.joel.finalassignment.repository

import com.joel.finalassignment.api.APIRequest
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.api.UserAPI
import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.response.ServerResponse

class UserRepository():APIRequest() {
    val userService = ServiceBuilder.retroService(UserAPI::class.java)
    suspend fun registerUser(user:UserTable):ServerResponse
    {
        return apiRequest {
            userService.registerUser(user)
        }
    }

    suspend fun loginUser(username:String,password:String):ServerResponse
    {
        return apiRequest {

            userService.loginUser(username,password)
        }
    }
}