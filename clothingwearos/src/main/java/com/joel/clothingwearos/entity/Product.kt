package com.joel.clothingwearos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val _id:String="",
    val pName:String?=null,
    val pPrice:String?=null,
    val pDesc:String?=null,
    val pImage:String?=null,
    val pRating :String?=null
)