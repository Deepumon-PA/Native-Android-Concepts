package com.deepu.myandroidapp.feature_video_call_agora.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class VideoRoomViewModel: ViewModel() {

    private val _roomName = mutableStateOf(RoomTextFieldState())
    val roomNameField: State<RoomTextFieldState> get() = _roomName  //  or  val roomNameState: State<RoomTextState> = _roomNameState


    private val _onJoinEvent  = MutableSharedFlow<String>()
    val onJoinEvent get() =  _onJoinEvent.asSharedFlow()

    fun onRoomEnter(name: String){
       _roomName.value = _roomName.value.copy(
           text = name
       )
    }

    fun onJoinRoom(){
        if (_roomName.value.text.isBlank()){
            _roomName.value = _roomName.value.copy(
                        error = "The room can't be empty"
            )
            return
        }

        viewModelScope.launch {
            _onJoinEvent.emit(roomNameField.value.text)     //it's a one time event so use a sharedFlow to send one time data
        }
    }

}