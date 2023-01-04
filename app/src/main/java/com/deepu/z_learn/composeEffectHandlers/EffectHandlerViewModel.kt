package com.deepu.z_learn.composeEffectHandlers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class EffectHandlerViewModel: ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackBar("Hello there!"))
        }
    }


    sealed class ScreenEvents{
        data class ShowSnackBar(val message: String): ScreenEvents()
        data class DoSomethingElse(val message: String): ScreenEvents()
    }


}