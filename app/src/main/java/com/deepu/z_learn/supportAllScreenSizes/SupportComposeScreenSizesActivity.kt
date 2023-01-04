package com.deepu.z_learn.supportAllScreenSizes

import android.os.Bundle
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SupportComposeScreenSizesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val windowInfo = rememberWindowInfo()

            if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(10) { it->
                        Text(
                            text = "Hello $it",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Cyan)
                                .padding(16.dp)
                        )
                    }


                    items(10) { it->
                        Text(
                            text = "Hello $it",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green)
                                .padding(16.dp)
                        )
                    }

                }
            }else{
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {

                        items(10) { it->
                            Text(
                                text = "Hello $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Cyan)
                                    .padding(16.dp)
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ){

                        items(10) { it->
                            Text(
                                text = "Hello $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                                    .padding(16.dp)
                            )
                        }
                    }

                }
            }



        }
    }
}