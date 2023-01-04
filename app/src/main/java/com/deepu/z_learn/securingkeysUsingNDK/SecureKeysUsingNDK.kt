package com.deepu.z_learn.securingkeysUsingNDK

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.deepu.myandroidapp.R

// hackers can reverse engineer compiled code and find out the api keys from it
// but using ndk we can hide the keys inside C/C++ code files which can still be reverse engineered, but it is more difficult (hexadecimal editors can open it but difficult to find the keys)
// so it adds another layer of security over it

class HideKeyUsingNDKActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide_key_using_ndk)
        Log.d(TAG,"Hidden key is: ${getHiddenAPIKeyFromNDK()}")
    }


    private fun getHiddenAPIKeyFromNDK(){

    }

    companion object{
        private const val TAG = "SecureKeysUsingNDKLog"
    }


}