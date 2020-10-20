package com.example.alarm.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.R
import kotlinx.android.synthetic.main.alarm_item_row.view.*


class AlarmAdapter(
    var itemList: List<Alarm>,
    private var onItemClick: (Alarm) -> Unit
) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.alarm_item_row, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount() = itemList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(alarm: Alarm){
            itemView.itemAlarmTime.text = alarm.date.toString()
            itemView.itemAlarmName.text = alarm.name
            itemView.alarmSwitch.isChecked = alarm.isActive

            itemView.setOnClickListener{
                onItemClick(alarm)
            }
        }
    }
}