package com.deepu.z_learn.tabletOrNot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.res.booleanResource
import com.deepu.myandroidapp.R

class TabletOrNotActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
               val isTablet = booleanResource(id = R.bool.is_tablet)
                if(isTablet){
                    Text(text = "you are on a tablet")
                }else{
                    Text(text = "you are not on a tablet")
                }
            }
        }
    }
}