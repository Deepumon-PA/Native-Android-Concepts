package com.deepu.z_learn

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Callback

class LearnViewModel(
    private val savedStateHandle: SavedStateHandle //to get state during process death
) : ViewModel() {

    companion object {
        private const val TAG = "LearnViewModelTAG"
    }

    private val _liveData: MutableLiveData<String> = MutableLiveData("hi")
    val liveData get() = _liveData

    private val _stateFlow = MutableStateFlow("hi")
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow get() = _sharedFlow

    fun triggerLiveData() {
        _liveData.value = "I am LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "I am StateFlow"
    }

    fun triggerFlow(): Flow<String> {
        return flow {

            repeat(5) {
                emit("Item $it")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("I am shared flow")
        }
    }


//------------------------------Android Process death-----------------------------------------------------------
//Android application process can be killed at anytime if it is in paused or stopped state , when it is sent to recent apps section
// for eg, when we access the count value with the following code, if somehow process death happens, it can lose the state
//to avoid this , we can make

    var pdCounter by mutableStateOf(savedStateHandle.get("counter")?:0)
    private set

    fun incrementPdCount(){
        pdCounter++
        savedStateHandle.set("counter", pdCounter)
    }

    //------------------deep dive into kotlin flows and flow operators--------------------------------------------
    //flows are coroutines which emit values repeatedly
    //can be considered as  part of a reactive programming framework


    val countDownFlow = flow<Int> {
        val startingValue = 10

        var currentValue = startingValue

        while (currentValue > 0) {
            delay(1000L)
            emit(currentValue)
            currentValue--

        }
    }

    val countDownFlow1 = (1..10).asFlow() // will emit 1 to 10 with a delay of 1 sec

    fun startCounter() {

        viewModelScope.launch {

            countDownFlow.filter { count ->
                count % 2 == 0              // collected values will get filtered based on this boolean expression

            }.map { count ->
                count * count   // map the values with this expression or in other words transform the values
            }
                .onEach {   // doesn't transform the values but can access the values here. just like collect but will return the flow unlike collect which returns nothing rather finishes everything
                }
                .collect { //wont return the flow unlike onEach
//                    Log.d(TAG, "printing latest values $it")
                }

            //terminal flow operators: count, reduce, fold, take all emissions together and do something with it
            viewModelScope.launch {
                val count = countDownFlow1.filter {
                    it % 2 == 0
                }
                    .count { emission ->  // will print the count of emissions after executing the boolean expression
                        emission % 2 == 0
                    }
                Log.d(TAG, "terminal count operator: $count")

                val reduce =
                    countDownFlow1.reduce { accumulator, value -> // accumulator variable will store the accumulated result of the previous operation
                        accumulator + value
                    }

                Log.d(TAG, "terminal reduce operator result: $reduce")

                val fold =
                    countDownFlow1.fold(100) { accumulator, value -> // will add the result to the fold value provided
                        accumulator + value
                    }
                Log.d(TAG, "terminal fold operator result: $fold")

            }

            //flattening operators: will combine multiple flows into a single flow
            //flattening a list means: converting a list of lists into a single list by combining items of the inner lists

            val flatteningFlow1 = (1..2).asFlow()
            viewModelScope.launch {
                flatteningFlow1.flatMapConcat { value ->

                    //real world scenario example : processCall(id)
                    flow {
                        emit(value + 1)
                        delay(500L)
                        emit(value + 2)
                    }
                }.collect { flatValue ->
                    //each emission from flatteningFlow1 gets caught in the inner flow and processed
                    //result will be: 2,3,3,4
                    //flatMapMerge: won't wait for emission to finish, each emission will be processed simultaneously
                    //flatMapLatest: process the latest emission and ignore the previous emissions
                    print("The flatMapConcat value is $flatValue")
                }
            }

        }


        //buffer, conflate and collectLatest flow operators
        val dishes = flow<String> {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main Dish")
            delay(100L)
            emit("Dessert")
        }

        viewModelScope.launch {
            dishes.onEach {
                Log.d(TAG, "FLOW: $it is delivered")
            }
//                .collectLatest {  } // will ignore all previous emissions and only consider the latest emission
//                .conflate() // it will drop all emissions which are in queue when the collection completes and will go for the latest emission
//                .buffer() // will make sure that code inside collect block will run in a different coroutine as compared to  the emissions, so flow emissions will go on even if the collector has not finished
                .collect {
                    Log.d(TAG, "FLOW: now eating $it")
                    delay(1500L)
                    Log.d(TAG, "FLOW: finished eating $it")
                }
        }

        //similarly we can collect the flow like this
//        countDownFlow.onEach {}.launchIn(viewModelScope)

    }


    //------------Deep dive into stateFlow and sharedFlow-----------------------------------------------------------------

    private val _myStateFlow = MutableStateFlow(0)
    val myStateFlow get() = _myStateFlow.asStateFlow()

    fun triggerMyStateFlow() {
        _myStateFlow.value += 1
    }


    //------------Random code-------------------------------------------


    private val _secretNumberStateFlow = MutableStateFlow(0)
    val secretNumberStateFlow get() = _secretNumberStateFlow


    private fun incrementSecretNumber(){
    }

    private fun decrementSecretNumber(){

    }

}