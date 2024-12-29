package com.suyash.loginexample

import android.app.Application
import androidx.room.Room
import com.suyash.loginexample.data.local.AppDatabase

class App : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meter-readings-db"
        ).build()
    }
}