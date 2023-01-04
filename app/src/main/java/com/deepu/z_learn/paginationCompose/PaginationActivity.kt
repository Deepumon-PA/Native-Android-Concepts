package com.deepu.z_learn.paginationCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

/**
 * not using jetpack pagination library, but an easier approach recommended by philip
 * due to some shortcomings of paging library (it is difficult to work on a single list item after loading)
 */
class PaginationActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

            }

        }
    }
}