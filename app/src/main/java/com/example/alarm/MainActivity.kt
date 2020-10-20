package com.example.alarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarm.model.Alarm
import com.example.alarm.model.AlarmAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var alarms: MutableList<Alarm> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sdf = SimpleDateFormat("hh:mm")
        val currentDate = sdf.format(Date())
        alarms.add(Alarm(2, currentDate, "Foootbaaaaaalllll", false))
        alarms.add(Alarm(2, currentDate, "Foootbaaaaaalllll", false))
        setupViews()
    }

    private fun setupViews() {

        alarmRecyclerView.layoutManager = LinearLayoutManager(this)
        alarmRecyclerView.adapter = AlarmAdapter(
            itemList = alarms,
            onItemClick = {
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.activity_current_alarm, null)
                val time = view.findViewById<TextView>(R.id.currentAlarmTime)
                val name = view.findViewById<TextView>(R.id.currentAlarmName)
                val delete = view.findViewById<Button>(R.id.deleteAlarm)
                time.text = it.date
                name.text = it.name
                delete.setOnClickListener(View.OnClickListener { view ->
                    alarms.remove(it)
                    dialog.dismiss()
                    setupViews()
                })
                dialog.setContentView(view)
                dialog.show()
            })

        val newAlarm = findViewById<Button>(R.id.newAlarm)

        newAlarm.setOnClickListener(View.OnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.activity_new_alarm, null)
            val newAlarmTime = view.findViewById<TextView>(R.id.newAlarmTime)
            val newAlarmName = view.findViewById<EditText>(R.id.newAlarmName)
            val addAlarm = view.findViewById<Button>(R.id.addAlarm)

            newAlarmTime.setOnClickListener {
                val cal = Calendar.getInstance()
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        newAlarmTime.text = SimpleDateFormat("HH:mm").format(cal.time)
                    }
                TimePickerDialog(
                    this,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }
            addAlarm.setOnClickListener(View.OnClickListener { view ->
                alarms.add(Alarm(2, newAlarmTime.text.toString(), newAlarmName.text.toString(), true))
                dialog.dismiss()
                setupViews()

            })
            dialog.setContentView(view)
            dialog.show()

        })

    }

}


