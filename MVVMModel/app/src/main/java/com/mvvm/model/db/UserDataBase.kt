package com.mvvm.model.db

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * UserDataBase.kt
 * Info:
 * Created by cxd on 2020-03-31 15:31
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}