package com.joel.finalassignment.api_entity

data class UserTable(
        var _id:String?=null,
        var fullName: String? = null,
        var userName: String? = null,
        var email: String? = null,
        var phone:  String? = null,
        var password: String? = null
)