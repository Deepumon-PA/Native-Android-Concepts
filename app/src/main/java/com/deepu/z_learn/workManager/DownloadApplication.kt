package com.deepu.z_learn.workManager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class DownloadApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel  = NotificationChannel(
                "download_channel",
                "Image download",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager = getSystemService(/*Context.NOTIFICATION_SERVICE*/NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}