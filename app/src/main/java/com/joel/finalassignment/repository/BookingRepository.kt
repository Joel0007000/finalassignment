package com.joel.finalassignment.repository

import com.joel.finalassignment.api.APIRequest
import com.joel.finalassignment.api.BookingAPI
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.entity.Booking
import com.joel.finalassignment.response.BookingResponse

class BookingRepository():APIRequest() {
    val bookingAPI = ServiceBuilder.retroService(BookingAPI::class.java)

    suspend fun bookFurniture(booking: Booking): BookingResponse
    {
        return apiRequest {
            bookingAPI.bookProduct(ServiceBuilder.token!!,booking)
        }
    }

    suspend fun retrieveBooking():BookingResponse
    {
        return apiRequest {
            bookingAPI.retrieveBooking(ServiceBuilder.token!!)
        }
    }

    suspend fun updateBooking(id:String,qty:Int):BookingResponse
    {
        return apiRequest {
            bookingAPI.updateBooking(ServiceBuilder.token!!,id,qty)
        }
    }

    suspend fun deleteBooking(id:String):BookingResponse
    {
        return apiRequest {
            bookingAPI.deleteBooking(ServiceBuilder.token!!,id)
        }
    }
}