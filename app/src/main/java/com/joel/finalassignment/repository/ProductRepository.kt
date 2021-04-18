package com.joel.finalassignment.repository

import com.joel.finalassignment.api.APIRequest
import com.joel.finalassignment.api.ProductAPI
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.response.ProductResponse

class ProductRepository():APIRequest() {
    var productapi = ServiceBuilder.retroService(ProductAPI::class.java)

    suspend fun getProducts(): ProductResponse {
        return apiRequest {
            productapi.getProduct()
        }
    }
}