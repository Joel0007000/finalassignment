package com.joel.finalassignment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val _id:String="",
    val pname:String?=null,
    val pprice:String?=null,
    val pdesc:String?=null,
    val pimage:String?=null,
    val prating :String?=null
) {
}