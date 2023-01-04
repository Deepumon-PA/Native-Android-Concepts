package com.deepumon.benchmark

import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.deepu.myandroidapp",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD // launch the app with a fresh start
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun scrollAndNavigate() = benchmarkRule.measureRepeated(
        packageName = "com.deepu.myandroidapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD // launch the app with a fresh start
    ) {
        pressHome()
        startActivityAndWait()

        addElementsAndScrollDown()
    }
}

fun MacrobenchmarkScope.addElementsAndScrollDown(){
    val button = device.findObject(By.text("Click me"))

    val list = device.findObject(By.res("item_list"))

    repeat(30){
        button.click()
    }

    device.waitForIdle()

    list.setGestureMargin(device.displayWidth / 5) //shrink down gesture margin, to avoid scroll gesture from accidentally triggering device gesture

    list.fling(Direction.DOWN) // scroll down

    device.findObject(By.text("Element 29")).click() // click the last element of the list

    device.wait(Until.hasObject(By.text("Detail: Element 29")), 5000) //
}