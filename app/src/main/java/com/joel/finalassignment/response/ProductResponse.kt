package com.joel.finalassignment.response

import com.joel.finalassignment.entity.Product

data class ProductResponse(
    val success:Boolean?=null,
    val data:MutableList<Product>
) {
}