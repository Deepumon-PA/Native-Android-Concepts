package com.deepu.myandroidapp.feature_destinations.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.deepu.myandroidapp.feature_destinations.presentation.country_details.CountryDetailsScreen
import com.deepu.myandroidapp.feature_destinations.presentation.home.HomeScreen
import com.deepu.myandroidapp.feature_destinations.presentation.loginRegistration.WelcomeScreen
import com.deepu.myandroidapp.ui.theme.DarkByzantineBlue
import com.deepu.myandroidapp.ui.theme.MyAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivityTag"
    }


    @ExperimentalFoundationApi
    @ExperimentalCoilApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

            val gyroListener: SensorEventListener = object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor, acc: Int) {
                    Log.d(TAG, "accuracy triggered")

                }
                override fun onSensorChanged(event: SensorEvent) {

                    Log.d(TAG, "triggered")

                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                }
            }

            sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        /* window.apply {
             clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
             addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
             decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
             statusBarColor = Color.Transparent.toArgb()
         }*/


        setContent {
            MyAndroidAppTheme() {

                var scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState) {

                    window.statusBarColor = Color(0xff2B2844).toArgb()


//                //change status bar color with this code
//                window.statusBarColor = MaterialTheme.colors.surface.toArgb()
//                window.navigationBarColor = MaterialTheme.colors.surface.toArgb()
//
//                @Suppress("DEPRECATION")
//                if (MaterialTheme.colors.surface.luminance() > 0.5f) {
//                    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
//                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                }
//
//                @Suppress("DEPRECATION")
//                if (MaterialTheme.colors.surface.luminance() > 0.5f) {
//                    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
//                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//                }

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home_screen"
                    ) {

                        composable("login_screen") {
                            window.statusBarColor = Color(0xff2B2844).toArgb()
                            WelcomeScreen(navController)
                        }
                        composable(
                            "registration_screen", ///{userName}}
//                        arguments = listOf(
//                            navArgument("userName") {
//                                type = NavType.StringType
//                            },
////                            navArgument("Password") {
////                                type = NavType.StringType
////                            }
//                        )home_screen
                        ) {
                            window.statusBarColor = Color(0xff2B2844).toArgb()

//                        val userName = remember {
//                            it.arguments?.getString("userName")
//                        }

                        }

                        composable(
                            "home_screen"
                        ) {
                            window.statusBarColor = DarkByzantineBlue.toArgb()
                            HomeScreen(navController = navController, scaffoldState = scaffoldState)
                        }

                        composable(
                            "country_details_screen"
                        ) {
                            window.statusBarColor = Color.Transparent.toArgb()
                            CountryDetailsScreen(navController = navController)
                        }
                    }

                }

            }


        }
    }
}
//
//
//@Preview(showBackground = true)
//@Composable
//fun ImageCard() {
//    Card(
//        shape = RoundedCornerShape(15f),
//        elevation = 10.dp,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp)
//            .background(
//                color = Color.Red,
////                shape = CutCornerShape(20.dp)
//            )
//    ) {
//
////        val customFonts = FontFamily(
////            Font(R.font.font_one),
////            Font(R.font.font_two)
////        )
//
//        Spacer(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(350.dp)
//        )
//
//        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(10.dp).background(Color.Red, CutCornerShape(32.dp))) {
//            var visible by remember {
//                mutableStateOf(true)
//            }
//            AnimatedVisibility(visible = visible,
//                enter = slideInVertically(
//                    // Slide in from 40 dp from the top.
////                    initialOffsetY = { with(density) { -40.dp.roundToPx() } }
//                ) + expandVertically(
//                    // Expand from the top.
//                    expandFrom = Alignment.Top
//                ) + fadeIn(
//                    // Fade in with the initial alpha of 0.3f.
//                    initialAlpha = 0.3f
//                ),
//                exit = slideOutVertically() + shrinkVertically() + fadeOut()) {
//                Text(
//                    text = buildAnnotatedString {
//                        withStyle(
//                            style = SpanStyle(color = Color.Blue, fontSize = 50.sp)
//                        ) {
//                            append("G")
//                        }
//                        append("oThere")
//                    },
//                    color = Color.Cyan,
//                    fontSize = 50.sp,
//                    fontFamily = FontFamily.SansSerif,/*, fontStyle = FontStyle.Italic*/
//                    fontWeight = FontWeight.Bold,
////                    textDecoration = TextDecoration.Underline,
////                style = TextStyle(
////                    color = Color.White,
////                    fontSize = 50.sp,
////                    fontFamily = FontFamily.SansSerif/*, fontStyle = FontStyle.Italic*/
////                ), fontWeight = FontWeight.Bold
//                )
//            }
//
//        }
//    }
//}


//some more useful modifiers
//.border(width = 4.dp, color = red700, CutCornerShape(32.dp))
//.graphicsLayer {
//    shadowElevation = 8.dp.toPx()
//    shape = CutCornerShape(32.dp)
//    clip = true
//}
//.background(color = greenLight700)

