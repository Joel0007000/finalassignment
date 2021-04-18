package com.joel.finalassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joel.finalassignment.entity.Product

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(product: MutableList<Product>)
    @Query("select * from Product")
    suspend fun getProducts():MutableList<Product>

    @Query("delete from Product")
    suspend fun deleteProducts()

}
