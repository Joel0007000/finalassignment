package com.joel.finalassignment.api_entity

data class UserTable(
        var _id:String?=null,
        var fullname: String? = null,
        var username: String? = null,
        var email: String? = null,
        var phone:  String? = null,
        var password: String? = null
)