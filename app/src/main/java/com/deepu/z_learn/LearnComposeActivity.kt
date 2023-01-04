package com.deepu.z_learn

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.ui.spacing
import com.deepu.myandroidapp.ui.theme.MyAndroidAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LearnComposeActivity : ComponentActivity() {

    //Preferences DataStore
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MyAndroidAppPrefDataStore") // can be accessed throughout the rest of the application as a singleton instance

    private val SECRET_INTEGER = intPreferencesKey("mySecretInteger")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAndroidAppTheme {

                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(
//                        LocalSpacing.current.medium  // accessing the composition local that we've created that provides spacing data
                        // or by creating it as a material theme extension itself
                        MaterialTheme.spacing.medium

                    )
                ) {

                    //Clean theming in compose
                    //Composition Local - eg: LocalContext.current

                    val learnViewModel = viewModel<LearnViewModel>()
                    val count = learnViewModel.startCounter()

//                    counter(learnViewModel = learnViewModel)
                    incrementOrDecrement()

                }


            }
        }



        GlobalScope.launch {
            val success = awaitConnect()
            if (success) {
                //do something
            } else {
                //do something
            }
        }
    }


    @Composable
    fun incrementOrDecrement(){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {

                }) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                        contentDescription = "Remove button"
                    )

                }

                Text(
                    text = "0",
                    style = TextStyle(
                        color = Color.Black
                    )
                )

                Button(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_add_24),
                        contentDescription = "Add Button"
                    )

                }

            }


        }
    }



    @Composable
    fun counter(learnViewModel: LearnViewModel) {

        //---------------Deep dive into stateFlow and sharedFlow-------------------------------------------
        val countValue = learnViewModel.myStateFlow.collectAsState(initial = 0)


        Button(onClick = { learnViewModel.triggerMyStateFlow() }
        ) {
            Text(text = "Count is ${countValue.value}")
        }
        //count down values passed

    }


    fun <T> ComponentActivity.getLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
        }
    }


    //suspendCoroutine for callbacks
    suspend fun doSomethingFunc(): Boolean {
        return suspendCoroutine { continuation ->

        }
    }

    //normal call back function

    fun startCallBack() {

    }

    // can be used to do something by suspending it
    suspend fun awaitConnect(): Boolean {
        return suspendCoroutine { continuation ->
            if (0 < 1) {

                continuation.resume(true)
            } else {
                continuation.resume(false)

            }

        }
    }

}


//call back interface function
interface MyCallBackInterface {

    fun doCallBackFunc(): Boolean
}




