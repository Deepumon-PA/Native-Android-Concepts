package com.deepu.myandroidapp.feature_sql_delight

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.deepu.myandroidapp.feature_sql_delight.ui.PlacesListScreen
import com.deepu.myandroidapp.ui.theme.MyAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

//Let's learn SQLDelight
//kotlin's  way of dealing with the SQLight database
//KMM support
//Define DB + SQLDelight will generate type-safe kotlin classes behind the scenes

@AndroidEntryPoint
class MainSQLDelightActivity: ComponentActivity() {

    companion object{
        private const val TAG = "MainSQLDelightActivityLog"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "inside onCreate")
        setContent {
            Log.d(TAG, "setContent is called")
            MyAndroidAppTheme {
                PlacesListScreen()
            }
        }
    }
}




