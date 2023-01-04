package com.deepu.myandroidapp.feature_video_call_agora.presentation

sealed class AgoraScreens(val route: String) {

    object RoomScreen : AgoraScreens("room_screen")

    object VideoCallScreen : AgoraScreens("video_call_screen")

}

