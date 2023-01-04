package com.deepu.myandroidapp.feature_video_call_agora.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi

const val APP_ID = "1154adf349124e3ca2845ae1294203f9"

class VideoCallActivity : ComponentActivity() {

    @ExperimentalPermissionsApi
    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(16.dp)
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "room_screen"
                    ) {

                        composable(
                            route = "room_screen",
                        ) {
                            RoomScreen(
                                onNavigate = navController::navigate,
                            )
                        }

                        composable(
                            route = "${AgoraScreens.VideoCallScreen.route}/{roomName}",
                            arguments = listOf(
                                navArgument(name = "roomName") {
                                    type = NavType.StringType
                                }
                            )
                        ) {

                            val roomName = it.arguments?.getString("roomName") ?: return@composable
                            VideoCallScreen(
                                roomName = roomName,
                                onNavigateUp = navController::navigateUp
                            )

                        }
                    }

                }
            }
        }
    }
}
