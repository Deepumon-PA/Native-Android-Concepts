package com.deepu.z_learn.pictureInPictureCompose

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.viewinterop.AndroidView
import com.deepu.myandroidapp.R

class PictureInPictureActivity : ComponentActivity() {

    class PiPActionReceiver: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
           println("Clicked on PIP action")
        }

    }

    private val isPiPSupported by lazy { // check PIP is supported on the device

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
        }else{
            false
        }
    }

    private var videoViewBounds = Rect()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AndroidView(
                    factory = { // Compose still yet doesn't have a video view composable, so use VideoView from androids view system
                        VideoView(it, null).apply {
//                            setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.video}")) // this os tje correct one
                            setVideoURI(Uri.parse("android.resource://$packageName"))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { //called when compose is positioned, for getting view bounds correctly
                            videoViewBounds = it.boundsInWindow().toAndroidRect()
                        }
                )
            }
        }

    }

    override fun onUserLeaveHint() { //called when user leaves active screen
        super.onUserLeaveHint()
        if(!isPiPSupported){
            return
        }
           updatedPIPParams()?.let { params ->
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   enterPictureInPictureMode(
                          params
                   )
               }
           }
    }

    private fun updatedPIPParams(): PictureInPictureParams? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PictureInPictureParams.Builder()
                .setSourceRectHint(videoViewBounds) // for a smooth animation when transitioning to PIP Mode
                .setAspectRatio(Rational(16, 9))
                .setActions(  // actions on PIP video player window
                   listOf(
                     /*  RemoteAction(
                             Icon.createWithResource( //create an icon by getting asset from resource
                                 applicationContext,
                              R.drawable.ic_baseline_baby_changing_station_24
                             ),
                           "Baby changing station",
                           "Baby changing station",
                            PendingIntent.getBroadcast(
                                applicationContext,
                                0,
                                Intent(applicationContext, PiPActionReceiver::class.java),
                                 PendingIntent.FLAG_IMMUTABLE
                            )
                       )*/
                   )
                )
                .build()
        } else  null
    }
}