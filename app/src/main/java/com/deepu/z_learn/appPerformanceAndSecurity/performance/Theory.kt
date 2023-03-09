package com.deepu.z_learn.performanceDebugging

import android.os.Trace

//Android Studio Profiler

// Profiling the Memory, CPU and Networking characteristics (> API 26(Oreo) )
//1st way: in android studio: goto profiler and add a new session
//2nd way: in phone: goto developer options and select system trace and load the file in android studio by selecting load file option

//1...
/*
   1. Create a new session/ either load from file or create a new profileable process( from emulator)

   2. Can profile CPU (System trace), MEMORY (Heap dump) and ENERGY statistics and record them to analyze and compare.

   3. Events will be shown as a dot on top (user interactions, )

   4.Can also manually add trace points (code shown below)

   4.
 */

//App inspection : This area lets you to inspect app network usage (how much data is being consumed by the app, upload&download rate). database and background tasks (jobs, Alarms, Workers, Wakelocks ...etc)

//App quality insights: quality insights with firebase


//Manually add trace points
class LearnAppProfiling {
    fun traceableCode(){
        Trace.beginSection("sample")
        //...........
        Trace.endSection()
    }
}