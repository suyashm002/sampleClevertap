package com.suyash.loginexample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suyash.loginexample.data.local.entity.MeterReading
import kotlinx.coroutines.flow.Flow

@Dao
interface MeterReadingDao {
    @Query("SELECT * FROM meter_readings ORDER BY timestamp DESC")
    fun getAllReadings(): Flow<List<MeterReading>>

    @Insert
    suspend fun insert(reading: MeterReading)
}