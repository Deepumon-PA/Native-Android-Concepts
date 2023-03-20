package com.deepu.z_learn.appPerformanceAndSecurityAndPrivacy.performance

/**
what all things to consider
 **/

//App speed, App size, Memory consumption, Battery Usage

/**
5 ways to improve app performance
 **/

//---------------1. use R8: A tool to optimize build before release---------------------------------------
// code obfuscation will be done, all classes and function will be renamed and will be protected from reverse engineering
//also removes unused classes and functions, optimize libraries as well, remove unused resources
//how to enable: minifyEnabled = true, shrinkResources = true
//Proguard:
//set proguard rules inside gradle file
//to explicitly keep a class from getting obfuscated, use @Keep annotation
//or alternatively go to proguard-rules file in gradle section and -keep proguard-rules packageName.*
//

// -------------2.Use Android Studio Profiler: --------------------------------------------------------
//find the bottlenecks of your app using profiler
// gives Insights on Networking, memory consumption, battery consumption, speed of the app


//--------------3.Cache whenever possible ---------------------------------------------------------
//cache data collected from network using Room or similar data base so that repeated network calls can be avoided


//--------------4.Get rid of memory leaks ----------------------------------------------------------------------------
//get rid of memory leaks using libraries such as leak canary


//--------------5.Optimize network usage --------------------------------------------------------------------
//keeping radio chips turned on for long would considerably increase battery consumption
//use work manager to postpone/schedule/batch send network requests

/**
 *  BenchMarking and Baseline Profiles
 */

// Benchmarking your app

/**
 * Measuring Jank and startup with MacroBenchmarks
 */

//What is janking?
//most phones run at a refresh rate of 60Hz per second, ie it redraws/ refreshes a frame/image 60 times a second
//so if some screens are lagging or is taking more time to load/inflate then it will fall down behind the 60Hz mark and result in Janking

//Why bother about app startup?
//More time taken for app startup will lead to poor user experience

//How to tackle these?
//existing microbenchmark focuses on CPU rules and other common measuring mistakes
//so android now has Macrobenchmark which can measure startup and jank (most importantly startup and scrolling measurements)

//How does it work?
// cold start the app -> compilation is in prograss
