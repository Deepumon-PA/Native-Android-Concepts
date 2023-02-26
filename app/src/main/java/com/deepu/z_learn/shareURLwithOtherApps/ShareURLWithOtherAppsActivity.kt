package com.deepu.z_learn.shareURLwithOtherApps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ShareURLWithOtherAppsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContent {

           MaterialTheme {
               OpenURLHomeContent {
                 shareLink("www.google.com")
               }
           }

       }
    }

    @Composable
    fun OpenURLHomeContent(urlClickListener: () -> Unit){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "www.google.com")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                urlClickListener()
            }) {
                Text(text = "Open url in another app")
            }
        }
    }



    fun Context.shareLink(url: String){


        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, url )
            type =  "text/plain"
        }


        val shareIntent = Intent.createChooser(
            sendIntent,
            null
        )

        startActivity(shareIntent)
    }


}