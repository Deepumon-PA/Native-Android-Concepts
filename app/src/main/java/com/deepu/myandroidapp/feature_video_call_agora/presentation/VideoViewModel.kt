package com.deepu.myandroidapp.feature_video_call_agora.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class VideoViewModel: ViewModel() {

    private val _hasAudioPermission  = mutableStateOf(false)
    val hasAudioPermission: State<Boolean> = _hasAudioPermission

    private val _hasCameraPermission = mutableStateOf(false)
    val hasCameraPermission = _hasCameraPermission


    fun onPermissionResult(
        acceptedAudioPermission: Boolean,
        acceptedCameraPermission: Boolean
    ){
        _hasAudioPermission.value = acceptedAudioPermission
        _hasCameraPermission.value = acceptedCameraPermission
    }


}