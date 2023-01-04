package com.deepu.z_learn.gradle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LearnGradleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

//Some notes

/*
 1.build.gradle(Project Level)
   configuration about project : dependency/library versions(inside BuildScript), plugins
 2.build.gradle(Module, App Level): Project specific configuration(like Android specific settings, compileSDkVersion..etc)
 3.Gradle is simple a build automator: takes all project configurations defined in these build files
  and execute different tasks in order.
cl
  Different versions of gradle: Gradle-groovy, then Gradle-kotlin (gradle-kotlin is strictly types as compared to groovy which is dynamically typed)

  Gradlew: gradle wrapper file : installs specific version of gradle as mentioned and runs specific tasks

 */