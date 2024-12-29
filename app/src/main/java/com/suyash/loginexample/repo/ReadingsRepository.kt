package com.suyash.loginexample.repo

import com.suyash.loginexample.data.local.dao.MeterReadingDao
import com.suyash.loginexample.data.local.entity.MeterReading

// Repository
class ReadingsRepository(private val meterReadingDao: MeterReadingDao) {
    fun getAllReadings() = meterReadingDao.getAllReadings()
    
    suspend fun insert(reading: MeterReading) {
        meterReadingDao.insert(reading)
    }
}