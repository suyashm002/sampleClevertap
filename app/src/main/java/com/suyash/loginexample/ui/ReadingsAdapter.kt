package com.suyash.loginexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suyash.loginexample.R
import com.suyash.loginexample.data.local.entity.MeterReading
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReadingsAdapter : ListAdapter<MeterReading, ReadingsAdapter.ReadingViewHolder>(ReadingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reading, parent, false)
        return ReadingViewHolder(view)
    }
    override fun onBindViewHolder(holder: ReadingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val valueText: TextView = itemView.findViewById(R.id.text_value)
        private val timestampText: TextView = itemView.findViewById(R.id.text_timestamp)

        fun bind(reading: MeterReading) {
            valueText.text = "%.2f".format(reading.value)
            timestampText.text = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
                .format(Date(reading.timestamp))
        }
    }
    class ReadingDiffCallback : DiffUtil.ItemCallback<MeterReading>() {
        override fun areItemsTheSame(oldItem: MeterReading, newItem: MeterReading) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MeterReading, newItem: MeterReading) =
            oldItem == newItem
    }
}