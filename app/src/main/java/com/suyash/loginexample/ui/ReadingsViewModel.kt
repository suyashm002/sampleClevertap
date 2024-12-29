package com.suyash.loginexample.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suyash.loginexample.App
import com.suyash.loginexample.data.local.entity.MeterReading
import com.suyash.loginexample.repo.ReadingsRepository

class ReadingsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReadingsRepository
    val readings: LiveData<List<MeterReading>>

    init {
        val dao = (application as App).database.meterReadingDao()
        repository = ReadingsRepository(dao)
        readings = repository.getAllReadings().asLiveData()
    }
}