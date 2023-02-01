package com.deepu.z_learn.protoDataStore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

//Proto datastore
//Type safe, Fully asynchronous,no synchronous support, follows flows inner exception handling mechanism
//handles data updates safely, easy data migrations, works with complex classes like enums and lists

//uses protocol buffers (language neutral, platform neutral mechanism for serializing structured data)(faster, smaller and simpler than xml)

class ProtoDataStoreActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isChecked = remember {
                mutableStateOf(false)
            }
            val input = remember {
                mutableStateOf("")
            }
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(text = "DataStore preferences and proto datastore",
                        color = Color.White, modifier = Modifier.align(CenterHorizontally))

                    TextField(colors = TextFieldDefaults.textFieldColors(textColor = Color.White), value = input.value , onValueChange = {
                        input.value  = it
                    }, label = {
                        Text("Enter text here", color = Color.White)
                    })

                   Row(
                       horizontalArrangement = Arrangement.Center,
                       verticalAlignment = Alignment.CenterVertically
                   ){
                       Text(text = "Save?", color = Color.Blue)
                       Checkbox(checked = isChecked.value, onCheckedChange = {
                           isChecked.value = it;
                       }, colors = CheckboxDefaults.colors(
                           checkedColor = Color.Red,
                           uncheckedColor = Color.White
                       ))
                   }
                }
            }
        }
    }

}

//schema

