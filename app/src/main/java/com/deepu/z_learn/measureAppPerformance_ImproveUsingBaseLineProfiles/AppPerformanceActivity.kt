package com.deepu.z_learn.measureAppPerformance_ImproveUsingBaseLineProfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

class AppPerformanceActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                    var counter by remember {
                        mutableStateOf(0)
                    }

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "start",
                        modifier = Modifier.semantics {
                            testTagsAsResourceId = true
                        }
                    ) {
                        composable("start") {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .testTag("item_list")
                            ) {
                                item {
                                    Button(onClick = { counter++ }) {
                                        Text(text = "Click me")
                                    }
                                }
                                items(counter) {
                                    val text = "Element $it"
                                    Text(
                                        text = text,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                navController.navigate("detail/$text")
                                            }
                                            .padding(32.dp)
                                    )
                                }
                            }
                        }
                        composable(
                            route = "detail/{text}",
                            arguments = listOf(
                                navArgument("text") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val text = it.arguments?.getString("text") ?: "Default"
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Detail: $text")
                            }
                        }
                    }
            }
        }

    }
}
