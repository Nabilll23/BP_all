package com.example.notif_24123117

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
class AlarmReceiver : BroadcastReceiver() {
    private val channelId = "alarm_channel"
    override fun onReceive(context: Context, intent: Intent) {
        // Logika memunculkan notifikasi
        val channelId = "alarm_channel"
        val channelName = "Alarm Channel"

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Buat Channel (Wajib untuk Android O+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        // Builder Notifikasi
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Ganti dengan ikon anda
            .setContentTitle("Alarm Berbunyi!")
            .setContentText("Waktunya untuk melakukan aktivitas Anda.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
        notificationManager.notify(123, builder.build())
    }
}
