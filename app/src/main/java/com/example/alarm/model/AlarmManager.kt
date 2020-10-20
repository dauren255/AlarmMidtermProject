package com.example.alarm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AlarmManager(
    private val alarms: MutableList<Alarm>
): Parcelable {

    fun add(alarm: Alarm){
        alarms.add(alarm)
    }
    fun remove(alarm: Alarm){
        alarms.remove(alarm)
    }
    fun getAlarms(): MutableList<Alarm> {
        return alarms
    }
}