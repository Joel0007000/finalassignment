package com.joel.finalassignment.response

import com.joel.finalassignment.entity.User

data class ServerResponse(val success:Boolean?=null
                          , val data: User?=null)