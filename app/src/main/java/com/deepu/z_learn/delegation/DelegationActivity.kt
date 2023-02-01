package com.deepu.z_learn.delegation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KProperty

//kotlin delegates
// can be used as a replacement for inheritance with very less overhead
/*
  problem with careless use of inheritance: for eg, in base activity concept, we are forced to write all of the functionality logics inside
  the BaseActivity itself (for eg: want to handle lifecycle events, handle deep links through intent filters ...etc), so it violates the "single source of truth" concept
 */
//Standard delegates and property delegates, two important delegate concepts in kotlin


//------------------INHERITANCE------------------------------------
open class Base{

    fun lifeCycleHandler(){ // functionality 1

    }

    fun logging(){      // functionality 2

    }

}

open class Base2
{
    fun anotherFunctionality(){ //another functionality

    }
}

class Derived: Base(){

}

fun main(){
    val der = Derived()
    der.lifeCycleHandler()
}
// both functionalities are inside the same base class in this case
// if we split it into different classes, it won't work with inheritance as a class cannot inherit from multiple classes, multiple inheritance is not supported

//------------------------STANDARD DELEGATION--------------------------------------------------------
class DelegationActivity: ComponentActivity(), LifeCycleHandler by LifeCycleHandlerImpl(), DeepLinkHandler by DeepLinkHandlerImpl() { //A class can implement multiple interfaces, so we can have any number of delegates

    //Property delegation
    val obj1 by lazy {
        print("print something")
        30
    }

    val obj2 by MyLazy {  // custom lazy implementation using delegation
        print("Hi")
        30
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLifeCycleHandler(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(this, intent)
    }
}


interface LifeCycleHandler{
    fun addLifeCycleHandler(owner: LifecycleOwner)
}

interface DeepLinkHandler{
    fun handleDeepLink(activity: Activity?, intent: Intent?)
}

class LifeCycleHandlerImpl: LifeCycleHandler, LifecycleEventObserver{

    //functionality 1
    override fun addLifeCycleHandler(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
       when(event){
           Lifecycle.Event.ON_CREATE ->{
               print("OnCreate")
           }

           Lifecycle.Event.ON_RESUME ->{
               print("OnResume")
           }

           else ->{
               print("In a life cycle method")
           }
       }
    }

}

class DeepLinkHandlerImpl: DeepLinkHandler{
    override fun handleDeepLink(activity: Activity?, intent: Intent?) {
        //do something
    }
}

//-------------------------------PROPERTY DELEGATION---------------------------------------------
//we can customize the behaviour of a property using property delegation
//for eg: change the getter and setter of a property

class MyLazy<out T: Any?>(
    private val initialize:() -> T
) {
    private var value:T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T{
         return if(value == null){
             value = initialize()
             return value!!
         }else
             value!!
    }
}
