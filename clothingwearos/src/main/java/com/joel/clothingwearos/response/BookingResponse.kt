package com.joel.clothingwearos.response

import com.joel.clothingwearos.entity.Booking

data class BookingResponse(
    val success:Boolean? = null,
    val data:MutableList<Booking>?=null,
    val message:String?=null
)