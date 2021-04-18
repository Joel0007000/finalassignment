package com.joel.finalassignment.response

import com.joel.finalassignment.entity.Booking

data class BookingResponse(
        val success:Boolean? = null,
        val data:MutableList<Booking>?=null,
        val message:String?=null
)