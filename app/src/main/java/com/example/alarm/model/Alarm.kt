package com.example.alarm.model

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Alarm(
    val id: Long,
    val date: String,
    val name: String,
    val isActive: Boolean
): Parcelable {

}