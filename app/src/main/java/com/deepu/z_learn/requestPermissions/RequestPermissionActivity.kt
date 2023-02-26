package com.deepu.z_learn.requestPermissions

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Refer the Profile section of Moneymana app, so see the latest way to request permissions in jetpack compose
 */

//1.InstallTime permissions
// poses limited risk to users privacy and out of app's sandbox, eg: internet, read phone state, read calender
//just need to declare in the manifest

// 2. RunTime permissions
//gives additional access to users private data, eg: camera, location..etc

/**
 * Request Permissions
 */
class RequestPermissionActivity: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{



        }
    }
}


//In XML projects (Old method)
/*
class RequestPermissionInXMLProjects: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var isPermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)

         val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { multiplePermissions ->
                if (checkPermissionResults(multiplePermissions)) {
                    //do something
                }
            }

        requestPermissionLauncher.launch(mutableListOf<String>().toTypedArray())
    }

    fun checkPermissionResults(multiplePermissions: Map<String, Boolean>): Boolean {
        for (i in multiplePermissions.values) {
            if (!i) {
                return false
            }
        }
        return true
    }

}
*/
