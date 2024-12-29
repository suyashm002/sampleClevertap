package com.suyash.loginexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suyash.loginexample.data.local.dao.MeterReadingDao
import com.suyash.loginexample.data.local.entity.MeterReading

@Database(entities = [MeterReading::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meterReadingDao(): MeterReadingDao
}