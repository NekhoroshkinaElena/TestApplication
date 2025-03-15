package com.nekhoroshkina.testapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nekhoroshkina.testapplication.registration.data.db.UserDao
import com.nekhoroshkina.testapplication.registration.data.db.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
