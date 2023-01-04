package com.deepumon.benchmark

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    val baseLineRule = BaselineProfileRule()

    @Test
    fun generateBaseLineProfile() = baseLineRule.collectBaselineProfile(            //as the name says this function will generate a text file
        packageName = "com.deepu.myandroidapp"
    ){
        pressHome()
        startActivityAndWait()

        addElementsAndScrollDown()
    }
}