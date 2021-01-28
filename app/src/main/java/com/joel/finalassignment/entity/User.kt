package com.joel.finalassignment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
var fname: String? = null,
var usename: String? = null,
var email: String? = null,
var phoneNo:  String? = null,
var password: String? = null
){
    @PrimaryKey(autoGenerate=true)
    var usedId : Int = 0
}