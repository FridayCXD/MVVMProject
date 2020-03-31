package com.mvvm.model

import android.app.Application
import androidx.room.Room
import com.mvvm.model.db.UserDataBase

/**
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
        dataBase = Room.databaseBuilder(this, UserDataBase::class.java, "user_data.db")
            .allowMainThreadQueries()
            .build()

    }

    companion object {
        lateinit var application: App
        lateinit var dataBase: UserDataBase
    }
}