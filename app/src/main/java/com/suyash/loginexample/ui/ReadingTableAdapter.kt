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

class ReadingTableAdapter : ListAdapter<MeterReading, ReadingTableAdapter.TableViewHolder>(ReadingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reading_table, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateText: TextView = itemView.findViewById(R.id.text_date)
        private val valueText: TextView = itemView.findViewById(R.id.text_value)

        fun bind(reading: MeterReading) {
            dateText.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                .format(Date(reading.timestamp))
            valueText.text = "%.2f".format(reading.value)
        }
    }
    class ReadingDiffCallback : DiffUtil.ItemCallback<MeterReading>() {
        override fun areItemsTheSame(oldItem: MeterReading, newItem: MeterReading) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MeterReading, newItem: MeterReading) =
            oldItem == newItem
    }
}