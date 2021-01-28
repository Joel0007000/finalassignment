package com.joel.finalassignment.dao

import androidx.room.Dao
import androidx.room.Insert
import com.joel.finalassignment.entity.User

@Dao
interface UserDAO {

    //insert user
    @Insert
    suspend fun userSignUp (user: User)
}