package com.joel.finalassignment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var   _id:String="",
var fname: String? = null,
var username: String? = null,
var email: String? = null,
var phoneNo:  String? = null,
var password: String? = null
)