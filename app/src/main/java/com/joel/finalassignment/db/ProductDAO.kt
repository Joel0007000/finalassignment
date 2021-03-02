package com.joel.finalassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joel.finalassignment.entity.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun  insert(product: Product)
    @Query("select * from Product")
    suspend fun getProducts():MutableList<Product>

}
