package com.deepu.z_learn.localNotifications

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.deepu.myandroidapp.ui.theme.MyAndroidAppTheme

class LocalNotifications: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {

            MyAndroidAppTheme{

            }

        }
    }
}