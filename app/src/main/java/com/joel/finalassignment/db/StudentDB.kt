package com.joel.finalassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joel.finalassignment.dao.UserDAO
import com.joel.finalassignment.entity.Product
import com.joel.finalassignment.entity.User

@Database(
        entities = [User::class,Product::class],
        version = 4,
    exportSchema = false
)

abstract class StudentDB : RoomDatabase(){

    abstract fun getUserDAO() : UserDAO
    abstract fun getProductDAO():ProductDAO

    companion object{
        @Volatile
        private var instance: StudentDB? = null

        fun getInstance(context: Context): StudentDB{
            if(instance == null){
                synchronized(StudentDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        StudentDB::class.java,
                        "ClothingDB"
                ).fallbackToDestructiveMigration().build()
    }

}