package com.suyash.loginexample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data Classes and Database
@Entity(tableName = "meter_readings")
data class MeterReading(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Float,
    val timestamp: Long,
    val imagePath: String
)