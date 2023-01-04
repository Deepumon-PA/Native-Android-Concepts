package com.deepu.myandroidapp.common

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.deepu.myandroidapp.R
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//instrumentation test . testing android components

@RunWith(JUnit4::class)
class SampleAndroidComponentTest{

    lateinit var sampleAndroidComponentTest: SampleAndroidComponent

    @Before
    fun setUp(){ // will setup initializations on the execution of every test case
        sampleAndroidComponentTest = SampleAndroidComponent()
    }

    @After
    fun tearDown(){
        //will get executed once the test is completed,
        //can be used to release memory occupied by instantiations
        //objects are automatically garbage collected so no need to clear them here
    }


    @Test
    fun passedStringIsTheOneWithResId_isTrue(){
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        val result = sampleAndroidComponentTest.checkIfTheStringIsSameAsTheOneWithResourceId(appContext, R.string.app_name, "MyAndroidApp")
        assertThat(result).isTrue()
    }

}