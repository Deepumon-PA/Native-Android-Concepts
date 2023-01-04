package com.deepu.myandroidapp.feature_video_call_agora.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RoomScreen(
    onNavigate: (String) -> Unit,
    videoRoomViewModel: VideoRoomViewModel = viewModel()
) {
    //since it only needs to be executed once use hardcoded value "true" as the key
    LaunchedEffect(key1 = true) {
        videoRoomViewModel.onJoinEvent.collectLatest { name ->
            onNavigate("${AgoraScreens.VideoCallScreen.route}/$name")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = videoRoomViewModel.roomNameField.value.text,
            onValueChange = videoRoomViewModel::onRoomEnter,
            modifier = Modifier.fillMaxWidth(),
            isError = videoRoomViewModel.roomNameField.value.error != null,
            placeholder = {
                Text(text = "Enter room name")
            }
        )

        videoRoomViewModel.roomNameField.value.error?.let {
            Text(text = it, color = MaterialTheme.colors.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                videoRoomViewModel.onJoinRoom()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(text = "Join", color = Color.White)
        }

    }

}