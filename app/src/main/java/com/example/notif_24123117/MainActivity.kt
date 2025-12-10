package com.example.notif_24123117

import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.jetbrains.annotations.ApiStatus
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge

    class MainActivity : AppCompatActivity() {

        private lateinit var timePicker: TimePicker
        private lateinit var tvStatus: TextView
        private lateinit var etPesan: EditText
        private lateinit var btnCancelAlarm: PendingIntent

        private val ALARM_REQUEST_CODE = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            timePicker = findViewById(R.id.timePicker)
            tvStatus = findViewById(R.id.tv_status)
            etPesan = findViewById(R.id.edit_text_pesan)
            val btnSetAlarm: Button = findViewById(R.id.btn_set_alarm)
            btnCancelAlarm = findViewById(R.id.btn_cancel_alarm)



            btnSetAlarm.setOnClickListener {

                // Potongan kode di dalam setOnClickListener tombol
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.SECOND, 0)

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)

// PendingIntent FLAG_IMMUTABLE wajib untuk Android 12+
                val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

// Set Alarm
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (alarmManager.canScheduleExactAlarms()) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

                    } else {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                    }
                } else {
                    Toast.makeText(this, "Alarm gagal diatur!", Toast.LENGTH_SHORT).show()
                }

                tvStatus.text = "Alarm berhasil diatur untuk pukul ${timePicker.hour}:${String.format("%02d", timePicker.minute)}"
                Toast.makeText(this, "Alarm diatur!", Toast.LENGTH_SHORT).show()

            }
        }
    }