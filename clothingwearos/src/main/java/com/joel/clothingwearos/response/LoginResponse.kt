package com.joel.clothingwearos.response

import com.joel.clothingwearos.entity.User

data class LoginResponse(
    val success:Boolean?=null,
    val token:String?=null,
    val data: User?=null,
    val message:String?=null
)