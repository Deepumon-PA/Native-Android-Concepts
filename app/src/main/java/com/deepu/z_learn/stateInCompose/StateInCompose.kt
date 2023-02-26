package com.deepu.z_learn.stateInCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deepu.myandroidapp.ui.theme.MyAndroidAppTheme

/**
 * State in compose
 */

//persist state across re-composition: 'remember
//persist state across configuration changes: 'rememberSaveable'
//hoist the state for re-usability and testability
//use viewmodel with LiveData and 'observeAsState'

/**
 * State hoisting: lifting the state up or moving state to a composable's caller to make the composable stateless
 */

class StateInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidAppTheme {
                Surface {
                    StateDemoHome()
                }
            }

        }
    }
}

class StateDemoViewModel : ViewModel() {
    var _name: MutableLiveData<String> = MutableLiveData("");
    val name: LiveData<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}


@Composable
fun StateDemoHome(stateDemoViewModel: StateDemoViewModel = viewModel()) {
    //observeAsState is also functions like rememberState
    val name: String by stateDemoViewModel.name.observeAsState("")

/*
    var name by remember {
        mutableStateOf("")
    }
*/

    //rememberSaveable will make the field also survive configuration changes
//    var name by rememberSaveable {
//        mutableStateOf("")
//    }

    StateDemoContent(name) { stateDemoViewModel.onNameChange(it) }
}

@Composable
fun StateDemoContent(name: String, onNameChanged: (String) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = name,
            onValueChange = onNameChanged,
            textStyle = TextStyle(color = Color.Black)
        )

    }

}


