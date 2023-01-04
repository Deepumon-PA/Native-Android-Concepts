package com.deepu.z_learn.sealedvsenums

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SealedVsEnumActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            //Handling using sealed classes
            //sealed classes would offer
            val error: HttpError = HttpError.NotFound
            error.errorCode // can access the data like this as well
            when (error) {
                HttpError.NotFound -> {

                }
                HttpError.UnAuthorized ->{

                }
                HttpError.Forbidden -> {}
            }

            //Handling using enum classes
            val errorEnum: HttpErrorEnum = HttpErrorEnum.NotFound
            HttpErrorEnum.values().forEach (::println)
            when (errorEnum) {
                HttpErrorEnum.NotFound -> {

                }
                HttpErrorEnum.UnAuthorized ->{

                }
                HttpErrorEnum.Forbidden -> {}
            }


        }

    }


}