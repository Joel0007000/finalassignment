package com.joel.finalassignment.repository

import com.joel.finalassignment.entity.User

data class LoginResponse(
        val success:Boolean?=null,
val token:String?=null,
val data:User?=null,
        val message:String?=null
        )