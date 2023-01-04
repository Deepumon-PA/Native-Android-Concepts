package com.deepu.z_learn.executeCodeOnBootUp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_BOOT_COMPLETED){
            Log.d("MAABootTag","Device boot completed, app is setting up")
        }
    }
}