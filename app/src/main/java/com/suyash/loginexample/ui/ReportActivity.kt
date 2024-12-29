package com.suyash.loginexample.ui
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.suyash.loginexample.R
import com.suyash.loginexample.data.local.entity.MeterReading
import com.suyash.loginexample.ui.ReadingTableAdapter
import com.suyash.loginexample.ui.ReadingsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportActivity : AppCompatActivity() {
    private lateinit var chart: LineChart
    private lateinit var tableAdapter: ReadingTableAdapter
    private val viewModel: ReadingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        setupChart()
        setupTable()
        observeReadings()
    }

    private fun setupChart() {
        chart = findViewById(R.id.line_chart)
        chart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisRight.isEnabled = false
        }
    }

    private fun setupTable() {
        tableAdapter = ReadingTableAdapter()
        findViewById<RecyclerView>(R.id.readings_table).apply {
            layoutManager = LinearLayoutManager(this@ReportActivity)
            adapter = tableAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeReadings() {
        viewModel.readings.observe(this) { readings ->
            updateChart(readings)
            tableAdapter.submitList(readings)
            updateSummary(readings)
        }
    }

    private fun updateChart(readings: List<MeterReading>) {
        val entries = readings.mapIndexed { index, reading ->
            Entry(reading.timestamp.toFloat(), reading.value)  // Using timestamp for X-axis
        }

        val dataSet = LineDataSet(entries, "Readings").apply {
            color = ContextCompat.getColor(this@ReportActivity, R.color.purple_700)
            setCircleColor(ContextCompat.getColor(this@ReportActivity, R.color.purple_700))
            lineWidth = 2f
            circleRadius = 4f
            valueTextSize = 10f
        }

        chart.data = LineData(dataSet)

        // Format X-axis to show dates
        chart.xAxis.valueFormatter = object : ValueFormatter() {
            private val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())

            override fun getFormattedValue(value: Float): String {
                return dateFormat.format(Date(value.toLong()))
            }
        }

        chart.invalidate()
    }

    private fun updateSummary(readings: List<MeterReading>) {
        if (readings.isEmpty()) return

        val average = readings.map { it.value }.average()
        val max = readings.maxOf { it.value }
        val min = readings.minOf { it.value }

        findViewById<TextView>(R.id.text_average).text = "Average: %.2f".format(average)
        findViewById<TextView>(R.id.text_max).text = "Maximum: %.2f".format(max)
        findViewById<TextView>(R.id.text_min).text = "Minimum: %.2f".format(min)
    }
}