package com.deepu.myandroidapp.feature_video_call_agora.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import io.agora.agorauikit_android.AgoraConnectionData
import io.agora.agorauikit_android.AgoraVideoViewer

private const val TAG = "VideoCallScreenTAG"

@ExperimentalPermissionsApi
@ExperimentalUnsignedTypes
@Composable
fun VideoCallScreen(
    roomName: String,
    onNavigateUp: () -> Unit,
    videoViewModel: VideoViewModel = viewModel()
) {

    val agoraView: AgoraVideoViewer? = null

    //list all the permissions here
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.CAMERA
        )
    )

    // life cycle owner is required in the activity
    // since this compose is called from an activity, we can get the lifecycle owner here
    val lifeCycleOwner = LocalLifecycleOwner.current


    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                permissionsState.launchMultiplePermissionRequest()
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
    )

    //simple way of asking permissions in compose, method 1
    /* val permissionLauncher =
         rememberLauncherForActivityResult(
             contract = ActivityResultContracts.RequestMultiplePermissions(),
             onResult =
             { perms ->
                 videoViewModel.onPermissionResult(
                     acceptedAudioPermission = perms[android.Manifest.permission.RECORD_AUDIO] == true,
                     acceptedCameraPermission = perms[android.Manifest.permission.CAMERA] == true
                 )
             }
         )

     LaunchedEffect(key1 = true) {
         permissionLauncher.launch(
             arrayOf(
                 android.Manifest.permission.RECORD_AUDIO,
                 android.Manifest.permission.CAMERA
             )
         )
     }*/

    BackHandler {
        agoraView?.leaveChannel()
        onNavigateUp()
    }

    composePermissions(permissionState = permissionsState, videoViewModel)

    if (videoViewModel.hasAudioPermission.value && videoViewModel.hasCameraPermission.value) {
        AndroidView(
            factory = {
                AgoraVideoViewer(
                    it,
                    connectionData = AgoraConnectionData(
                        appId = APP_ID
                    )
                ).also {
                    it.join(roomName)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }

}


@ExperimentalPermissionsApi
fun composePermissions(permissionState: MultiplePermissionsState, videoViewModel: VideoViewModel) {
    permissionState.permissions.forEach { perm ->
        when (perm.permission) {
            android.Manifest.permission.RECORD_AUDIO -> {
                when {
                    perm.hasPermission -> {
                        Log.d(TAG, "has audio permission")
                        videoViewModel.onPermissionResult(
                            acceptedAudioPermission = true,
                            acceptedCameraPermission = true
                        )
                    }

                    perm.shouldShowRationale -> {
                        Log.d(TAG, "rationale: allow this permission")
                    }

                    !perm.hasPermission && !perm.shouldShowRationale -> {
                        Log.d(TAG, "Please allow audio permission in app settings")
                    }
                }
            }
            android.Manifest.permission.CAMERA -> {
                when {
                    perm.hasPermission -> {
                        Log.d(TAG, "has camera permission")
                    }

                    perm.shouldShowRationale -> {
                        Log.d(TAG, "rationale: allow this permission")
                    }

                    !perm.hasPermission && !perm.shouldShowRationale -> {
                        Log.d(TAG, "Please allow camera permission in app settings")
                    }
                }
            }
        }
    }
}