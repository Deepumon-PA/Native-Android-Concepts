package com.deepu.myandroidapp

import android.app.Application
import com.deepu.z_learn.learnViewModelModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

open class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        //koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            module { listOf(learnViewModelModule) }

        }
    }
}
