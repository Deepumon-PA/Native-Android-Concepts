package com.deepu.z_learn.composeEffectHandlers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch


class LearnEffectHandlerActivity : ComponentActivity() {

    //-----------------------------------compose effect handlers--------------------------------------------
//side effect: something that escapes the scope of a compose function

    private var i = 0      //A private variable i
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val effectHandlerViewModel: EffectHandlerViewModel by viewModels()

            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                Button(onClick = { text += "#" }) {
                    i++           //wrong, use a launched effect instead
                    /*incrementing the private variable i inside a composable which results into a side effect
                    * i is not a composable state
                    * no control over the variable i since compose recomposition is not in our control either
                    * This is where effect handlers come into play
                    * */

                    Text(text = text)
                }

                //doing the right way - using effect handlers

                //1.Launched effect

                // below whenever the value of text changes coroutine will be cancelled and relaunched
                LaunchedEffect(key1 = text) {
                    //launched effect is a composable and  by default it provides a coroutine scope
                    delay(1000L)
                    println("our text is $text")

                }

                //fixed value true makes sure that it is never relaunched after each composition but only the first time
                LaunchedEffect(key1 = true) {
                    effectHandlerViewModel.sharedFlow.collect { event ->
                        when (event) {

                            is EffectHandlerViewModel.ScreenEvents.ShowSnackBar -> {

                                //do some operation

                            }
                            is EffectHandlerViewModel.ScreenEvents.DoSomethingElse -> {

                                //do some operation

                            }
                        }

                    }


                }
            }

            //another use of launched effect with a non-fixed key value
            @Composable
            fun LaunchedEffectAnimation(
                counter: Int
            ) {

                val animate = remember {
                    Animatable(0f)
                }

                LaunchedEffect(key1 = counter) { /*Here when the counter value changes
           the following code will get cancelled and relaunched with new value */
                    animate.animateTo(counter.toFloat())
                }

            }


            //3.rememberCoroutineScope

            @Composable
            fun rememberCoroutineScopeDemo() {

                val scope = rememberCoroutineScope()
                /*
                gives a coroutine scope that is aware of re-composition

                use it only in/for callbacks as shown below, don't use it like simple LaunchedEffect

                mostly you don't need to use this if you are using viewModel scope
                 */

                Button(onClick = {
                    scope.launch {
                        delay(1000L)
                        print("hello world")
                    }
                }) {

                }

            }

            //3. rememberUpdateState

            @Composable
            fun RememberUpdateScopeDemo(
                onTimeOut: () -> Unit
            ) {

                val updatedOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
                /*
                here if we want to listen to changes in onTimeOut() use it as a rememberUpdatedState (or encapsulate it in a )
                so that even though LaunchedEffect will be called only once on the initial composition
                updateOnTimeOut will be called on every time it is triggered or changed
                 */
                LaunchedEffect(key1 = true) {
                    delay(3000L)
                    onTimeOut()         //wrong
                    updatedOnTimeOut()
                }

            }

            //4.DisposableEffect

            @Composable
            fun DisposableEffectDemo() {
                val lifeCycleOwner = LocalLifecycleOwner.current

                /*
                Here you can't use LaunchedEffect since the observer needs to be cleaned up or disposed

                 */
                DisposableEffect(key1 = lifeCycleOwner){

                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_PAUSE) {
                            print("onPause called")
                        }
                    }
                      lifeCycleOwner.lifecycle.addObserver(observer)

                    onDispose{ //should do the clean up inside this block
                        lifeCycleOwner.lifecycle.removeObserver(observer)
                    }

                }
            }


            //5.Side Effect
            //when you want to execute something on every recomposition
            @Composable
            fun SideEffectDemo(
                nonComposeCounter: Int
            ){
                SideEffect {
                      print("called after every successful recomposition")

                }
            }

            //6.ProduceState
            @Composable
            fun ProduceStateDemo(
                countUpTo: Int
            ): State<Int>{

                /*
                similar to a simple flow emitting values
                 */
                return produceState(initialValue = 0 ){
                    while (value < countUpTo){
                        delay(1000L)
                        value++
                    }
                }

            }

            //7.DerivedStateOf

            @Composable
            fun DerivedStateOf(){

                var counter by remember{
                    mutableStateOf(0)
                }

                val wrongText = "The counter is $counter" //every time you access this value  concatenation will take place
                //to avoid that use derived State of

                //it will cache the value on first access and when we access the value it's cached value will be passed
                //but when the value changes it will do the process again

                val counterText by derivedStateOf {
                    "The counter is $counter"
                }
                Button(onClick = { counter++ }) {
                    Text(text =  counterText)
                }

            }


            //8.snapshot Flow
            /*
            exact opposite of collectAsState

            take a compose state and create a flow out of that
             */
            @Composable
            fun SnapShotFlowDemo(){
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(key1 = scaffoldState){
                    snapshotFlow {
                        scaffoldState.snackbarHostState
                    }.mapNotNull { it.currentSnackbarData?.message }
                        .distinctUntilChanged()       //emit only if the value changed from the previous one
                        .collect { message ->
                            print("A snackbar with message: $message was shown")
                        }
                }
            }
        }

    }

}

