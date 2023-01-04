package com.deepu.z_learn


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//Learn dependency injection using kotlin koin

//module for viewModel dependency
 val learnViewModelModule = module {
  viewModel {
   LearnViewModel(get()) // is the basic syntax to get dependencies using koin, get() is a koin function which will create an instance of the specified dependency behind the scenes
  }

 //add this code inside application class

/* startKoin {
  androidLogger(Level.DEBUG)
  androidContext(this@MyApplication)
  module { listOf(com.deepu.z_learn.learnViewModelModule) }
 }*/
}

val repositoryModule = module {
  LearnRepository()
}

