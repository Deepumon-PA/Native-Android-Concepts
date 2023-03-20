package com.deepu.z_learn.appPerformanceAndSecurityAndPrivacy.performance.measureAppPerformance_using_benchmark_ImproveUsingBaseLineProfiles

//measure your apps performance including
/*
1. How long does it take to launch your app

2. Scrolling, clicking on an item, measuring how many frames are dropped
 */

// tool used: jetpack macro benchmark library, launch, scrolling , also micro benchmark is there

//in itself it is basically an instrumented test


// test result  value comparison

/*

  for startup test , check the medium value, which is the average of both min and max values


  FrameDurationCpuMs -> p50 --- P99 value should go from low to high
  FrameOverRunMs -> p50 --- P99  value should go from negative value to some high value, if its a negative value its too good

 */


//--------------------------Base line profiles---------------------------------------------------------------------------


/*
is just a text file where we specify the list of functions and classes which are already precompiled

we can generate these files to tell our app that it can access these precompiled which is faster than needing to compile them on the fly

can do it for libraries as well (example: jetpack compose uses it)

//for eg jetpack compose uses BaseLineProfiles(Unbundled), it is added as a library rather than bundled into android
//


//are list of classes and methods that are ahead of time compiled (AOT) at app installation (1.Rules for AOT compilation 2.Improve performance, 3. Ship with app or library)
//helps applications optimize startup, reduce jank
//increases the performance of apps
//improves cold startup performance by 40%

//How to implement
//Generate for your application, or generate and distribute rules with your library

*/