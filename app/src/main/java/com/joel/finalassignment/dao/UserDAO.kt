package com.joel.finalassignment.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joel.finalassignment.entity.User

@Dao
interface UserDAO {

    //insert user
    @Insert
    suspend fun userSignUp (user: User)

    @Query("select * from User")
    suspend fun authentication():User

    @Query("delete from User")
    suspend fun delete()
}