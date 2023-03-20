package com.deepu.z_learn.googlePlayReviewAPI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.google.android.play.core.review.ReviewManagerFactory

/**
 * In-App Review API allows users to add reviews staying within the app itself, no need to go to Play-store to log your review
 */
//1. Only applicable to production apps live in google play
//2. To test it, go to playStore -> in app sharing -> upload debug apk -> specify apk version -> add testing mail id's -> in phone's developer options allow app sharing option, get the app from playStore via link

class ReviewAPIActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFeedBackDialog()
        setContent {
            MaterialTheme(){
                Column(modifier = Modifier.fillMaxSize()) {
                    Button(onClick = {  }) {

                    }
                }
            }
        }
    }

    private fun showFeedBackDialog(){
        val reviewManager = ReviewManagerFactory.create(applicationContext)

        //returns an asynchronous task ,
        //first checks whether the package name is available in playStore, and has the quota that determines
        // the number of times the dialog is shown exceeded, if not it shows the dialog, else doesn't
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if(it.isSuccessful){
                reviewManager.launchReviewFlow(this, it.result)
            }
        }
    }
}