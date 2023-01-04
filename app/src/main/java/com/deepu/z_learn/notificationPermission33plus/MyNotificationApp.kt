package com.deepu.z_learn.notificationPermission33plus

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyNotificationApp: Application() {

    override fun onCreate() {
        super.onCreate()

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val notificationChannel =  NotificationChannel(
                "channel_id",
                "channel_name",
                NotificationManager.IMPORTANCE_HIGH
            )

           val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
           notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}