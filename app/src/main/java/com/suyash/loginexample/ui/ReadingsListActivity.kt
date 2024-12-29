package com.suyash.loginexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suyash.loginexample.R

class ReadingsListActivity : AppCompatActivity() {
    private val viewModel: ReadingsViewModel by viewModels()
    private lateinit var adapter: ReadingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readings_list)

        setupRecyclerView()
        observeReadings()
    }
    private fun setupRecyclerView() {
        adapter = ReadingsAdapter()
        findViewById<RecyclerView>(R.id.readings_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@ReadingsListActivity)
            adapter = this@ReadingsListActivity.adapter
        }
    }
    private fun observeReadings() {
        viewModel.readings.observe(this) { readings ->
            adapter.submitList(readings)
        }
    }
}
