package com.deepu.z_learn

import android.content.ContentValues
import android.content.Context
import android.media.AudioRouting
import android.media.MediaPlayer
import android.net.Uri
import android.os.Looper
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import java.util.logging.Handler
import kotlin.properties.Delegates
import kotlin.reflect.KProperty
import kotlin.system.measureTimeMillis

/**
 * This class is a reservoir to learn modern concepts in native android programming using kotlin
 **/

class BackingPropertyDemo {

    //-----------Backing property and backing Field--------------------------------------

    //Backing Field: used to access the a property from it's getter or setter

    private var _age: Long = 20L
    var age: Long = 0             //Backing property exposed to outside world
        get() {

            println("Age is $age") //will throw StackOverFlow exception since it will keep on calling itself indefinitely
            println("Age is $field") //backing field to access the original value of the backing property
            return field
        }
        set(value) {
            field = value

        }

}

class Backing {

    fun backingDemo() {
        var backingDemo = BackingPropertyDemo()
        backingDemo.age = 30
    }

}

fun main() {

//    val sac = StringAppendingClass()
//     println(sac.appendingFunction(sac.string1, sac.string2, sac.string3))

    val string1 = "Hi"
    val string2 = "Hello"
    val string3 = "How are you?"

//    println(string1.stringClassExtForAppending(string2, string3))
//    print(square(5))
//    printString("This is the string passed to print")

    DoSomethingWithLambdaClass { a: String, b: String -> /*print("concatenated result is $a and $b")*/ }

    //-----------------------------using interfaces instead of lambdas------------------------------------------
    val sumClass = SumClass()
    sumClass.funSum(5, 2, object : SumInterface {
        override fun executeSum(sum: Int) {
//            print("sum through interface is: $sum")
        }

    })

    //-----------------------------kotlin lambdas and higher order functions-------------------------------------
    val concatenateLambda: (String) -> Unit =
        { finalString: String -> /*print(finalString)*/ finalString }
    lambdaAcceptor("go", "forward", concatenateLambda)


    //-----------------------------kotlin filter and map lambda operators-----------------------------------------
    val myFriends = listOf<Person>(
        Person("Deepu", 27, 164),
        Person("Ben", 28, 170),
        Person("Ajai", 27, 174),
        Person("Riju", 30, 180),
        Person("Kartika", 27, 162),
    )

    val filteredList = myFriends.filter { person -> person.name.startsWith("R") }
//    print(filteredList)
    val mappedList = myFriends.filter { it.name.endsWith("u") }
        .map { person -> person.name }// define the operations inside lambda and it will return the appropriate list
//    print(mappedList)

    //-------------------kotlin scope functions let, apply, run, with and also-----------------------------------
    val cart = Cart()

    //with returns a lambda result
    val withValue = with(cart) {
        itemName = "Macbook"
        itemCount = 5
        "hi with function worked successfully"
    }

    //apply is similar as with but it returns the context object and it can access the member functions of the class
    cart.apply {
        itemName = "Samsung"
        itemCount = 6
    }.addTwoNumbers()

    //let's combine with and apply together
    val secondCartObject = Cart().apply {
        itemName = "Sony"
        itemCount = 7
    }

    with(secondCartObject) {
//        print("printing item name $itemName")
//        print("printing item count $itemCount")
    }

    val mySampleList = mutableListOf(1, 3, 5, 7)


    //also returns a list as well
    val modifiedList = mySampleList.also {
        it.removeAt(1)
//        print(it)
    }

    val duplicateCardObject =
        cart.also {  // returns a new modified object, can perform some more operations on the object
            it.itemCount = 50
        }
    //let, already knows it that is enough, most important usage is for null safety used along with null safety operator ?

    //run function is a combination of both with and let
    secondCartObject.run {
        itemName = "Hp Laptop"
        itemCount = 5
        "lambda result"
    }

    //-----------------------kotlin infix and tailrec functions------------------------------------------------
    //explained in detail below
    //---------------------kotlin coroutines--------------------------------------------
    funToTestCoroutine()

    //reified keyword
    genericTaker(/*String::class.java,*/ "") // no need to pass the generic class type when passing reified is used


    //---------------------------------Kotlin Delegation-----------------------------------------------------------------
    val mLoader = MediaLoader(FileDownloader("videoFile.mp4"), FilePlayer("videoFile.MP4"))
    mLoader.download()
    mLoader.play()


    //---------------------------------Content values: just like map you can store key values pairs------------------------
    val contentValues = ContentValues() // nowadays not widely used
    contentValues.put("ket1", 10)

    //------------------------SQLiteDatabase (old concepts)-----------------------------

    //for creating table
    //SQLiteDatabase sd = new SQLiteDataBase()
    // sd.execSQL("CREATE TABLE TABLE_NAME (+ id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL")
    // "name VARCHAR(255)"

    // for insertion SQLiteDatabase db = getWritableDatabase();
    //long id = db.insert("TABLE_NAME", null, values);
    //db.close()


    //for querying use Cursor class
    //SQLiteDatabase = getReadableDatabase()
    //Cursor cursor  = db.rawQuery("SELECT * FROM TABLE_NAME", null)
    //while(cursor != null)
    //while(cursor.moveToNext())
    //int intValue = cursor.getInt
    //String stringValue = cursor.getString
    //cursor.close
    //db.close


}

//-------------------WorkManager--------------------------------------------

/*

class MyCustomWorker(context: Context, workParams: WorkerParameters) : Worker(context, workParams) {
    override fun doWork(): ListenableFuture<Result> {
        return CallbackToFutureAdapter.getFuture {

        }
        // do the long running task here
    }
}
*/

fun workManagerDemo(context: Context) {

    // create constraints to apply
/*    var workConstraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiresStorageNotLow(true)
        .build()

    //frequency: one time request
    var myOneTimeWorkRequest = OneTimeWorkRequestBuilder<MyCustomWorker>()
        .setConstraints(workConstraints).build()
    var mySecondOneTimeWorkRequest = OneTimeWorkRequestBuilder<MyCustomWorker>()
        .setConstraints(workConstraints).build()
    var myThirsOneTimeWorkRequest = OneTimeWorkRequestBuilder<MyCustomWorker>()
        .setConstraints(workConstraints).build()
    var myFinalOneTimeWorkRequest = OneTimeWorkRequestBuilder<MyCustomWorker>()
        .setConstraints(workConstraints).build()

    WorkManager.getInstance(context).enqueue(myOneTimeWorkRequest)

    //frequency: periodic work
    var myPeriodicWorkRequest = PeriodicWorkRequestBuilder<MyCustomWorker>(20,TimeUnit.SECONDS)
        .setConstraints(workConstraints).build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork("My unique periodic work"
            ,ExistingPeriodicWorkPolicy.KEEP, myPeriodicWorkRequest)

    //chaining work
    WorkManager.getInstance(context)
        .beginWith(listOf(myOneTimeWorkRequest, mySecondOneTimeWorkRequest)) // these two works will run in parellel
        .then(myThirsOneTimeWorkRequest)  // then these two works will follow after the completion of the parallel works
        .then(myFinalOneTimeWorkRequest)
        .enqueue()*/

    //other features of work manager: BackOff Policy..tagging ..etc

}


//--------------------------synchronised-------------------------------------------

val lock = Any()
val synchronizedValue = synchronized(lock) {
    // any code that gets executed within this block is synchronized.. ie,  asynchronous access is blocked

}

//when using room, for non primitive entity values (eg: other Classes), use  @TypeConverter annotation, and specify it in database class
// as @TypeConverters(YourClass::class.java)


//---------------------------------Kotlin Delegation-----------------------------------------------------------------
//delegation is the concept of passing some functionality or task of an object to another object of the same type
//let's see how delegation works using "by" keyword

interface Downloader {
    fun download()
}

//task interfaces
interface Player {
    fun play()
}

class FileDownloader(private val file: String) :
    Downloader { // these are the delegating functionalities
    override fun download() {
        print("$file downloaded")

    }
}

class FilePlayer(private val file: String) : Player {
    override fun play() {
        print("$file played")
    }
}

class MediaLoader(
    private val downloader: Downloader, // this is where delegation is happening
    private val player: Player
) : Downloader, Player {
    override fun download() {
        downloader.download()
    }

    override fun play() {
        player.play()
    }
}

//kotlin supports delegation natively, so we can simplify the above code into

class MediaLoader2(private val downloader: Downloader, private val player: Player) :
    Downloader by downloader, Player by player // kotlin delegation

//property delegation

class PropertyDelegationClass {

    var someString: String? = null
        set(value) {
            if (value != null && value.length > 5) {
                field = value.trim().uppercase()
            }
        }


    var someString2: String? = null
        set(value) {
            if (value != null && value.length > 5) {
                field = value.trim().uppercase()
            }
        }

    //instead of the above approach we can use delegation to simplify it
    var someOtherString: String? by StringDelegation()
    var someOtherString2: String? by StringDelegation()


}

class StringDelegation { // this is the property delegation
    var formattedString: String? = null
    operator fun setValue( // by using operator keyword you can reuse a predefined function for a different use
        thisRef: Any?, // calling object
        property: KProperty<*>, //meta data
        value: String? //passed String
    ) {

        (thisRef as PropertyDelegationClass).someString // access the properties using thisRef
        if (value != null && value.length > 5) {
            formattedString = value.trim().uppercase()
        }

    }

    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): String? {
        return formattedString
    }
}


//predefined delegates (standard delegates) in kotlin  lazy, observable and vetoable

class PreDefDelClass1() {

}

class PreDefDelClass2() {
    var predefDelClass1 = PreDefDelClass1()

}

fun preDefDelFunc() {
    val predefDelClass2 =
        PreDefDelClass2() // will create an instance of PreDefDelClass2 and an instance of PreDefDelClass1, which is unnecessary

    //lazy delegation
    val predefDelClass2_1 by lazy {  //instance of PreDefDelClass2, but PreDefDelClass1 is not created, cool
        PreDefDelClass2()
    }

    predefDelClass2.predefDelClass1 // now the instance of PreDefDelClass1 is created

    //observable
    var someValue: Int by Delegates.observable(20) { // can observe value changes in this block
        //property is some meta information (don't bother)
            property, oldValue, newValue ->
        print(oldValue)
        print(newValue)
    }

    someValue = 40
    someValue = 50

    //vetoable
    var vetoableValue by Delegates.vetoable(50) { property, oldValue, newValue -> // assignment based on a vetoable condition
        print(oldValue)
        newValue > 60 // value will only be changed if this condition is satisfied
    }


}


data class Person(val name: String, var age: Int, val height: Int)

//---------------------kotlin coroutines--------------------------------------------

// thread, thread.sleep, delay, Globalscope.launch, runblocking, coroutine builders, async await and runblocking,
// launch, job.join, async and await(deferred), coroutine scope, coroutine dispatchers, coroutine context and coroutine scope
// coroutine context (dispatcher, job and coroutineName, confined, default, unconfined, main and io)
//composing suspend functions, sequential, concurrent and lazy execution start = Coroutine.lazy
//cancelling a coroutine, cancelandjoin, yield, cancellable suspending functions
//yield is also a cancellable function similar to delay function, but it wont delay
// isActive flag can be used to monitor if the current coroutine is cancelable or
//finally{withContext(NonCancellable){ }}, job.cancel(CancellationException("error message"))
// timeouts, withtimeout(2000){}, timeoutcancellationexception
//withtimeoutornull(){} can return a lambda result
//withcontext suspend function , cancellable,

// by default the app doesn't know whether the app has to wait for the coroutine to stop

fun funToTestCoroutine() =
    runBlocking { //runblocking will block the current thread till the completion of it's execution

        //coroutine builders

        launch {  // confined scope

        }

        println("going to invoke async")

        //every coroutine has its own scope which when printed will show you a unique hash value

        val asyncResult: Deferred<String> = async {
            println("inside async")
            delay(3000)
            "async task completed successfully"
        }

        println("result:  ${asyncResult.await()}") // will wait for the deferred result and executes once it's done,

        println("main code continues")

        val job =
            GlobalScope.launch {           // GlobalScope.launch will launch a coroutine in the application level, which will persist throughout the app's life cycle
                delay(1000)
            }

        job.join() // join will wait for the coroutine to complete before the execution of further code
        println("main code ends")


    }

val executionTime = measureTimeMillis {  // will calculate the execution time of code inside it
    //your code
}

//-----------------kotlin extension functions----------------------------------------

class StringAppendingClass {
    val string1 = "Hai"
    val string2 = "Hello"
    val string3 = "How are you?"
}

fun StringAppendingClass.appendingFunction(
    string1: String,
    string2: String,
    string3: String
): String {
    return "$string1 $string2 $string3"
}

// custom extension function in the inbuilt string class
fun String.stringClassExtForAppending(string1: String, string2: String): String {
    return this + string1 + string2
}

// practical use of extension functions is to manage code and make master classes with large amount of code smaller by moving code to
// utility classes with functions which  are extensions of these master classes
// for example if a view model is too big we can create a new utility class with functions extending view model
// and move functionalities to these functions, ie: MyViewModel.viewModelExtension(){}
// also we can get activity context in  a non activity utility class by extending a functions from Activity class
// ie, Activity.activityExtension(){ //can use 'this' here}

//-----------------------------kotlin lambdas and higher order functions-------------------------------------

// simple lambdas
private val square: (Int) -> Int = { number ->
    number * number
}

private val printString: (String) -> Unit = {
    print(it)
}

/*any function which accepts a lambda can be considered as a higher order function*/
private fun lambdaAcceptor(s1: String, s2: String, concatenateString: (String) -> Unit) {
    val concatenatedString = s1 + "Move" + s2
    concatenateString(concatenatedString)
}


// passing lambda functions into a class constructor
class DoSomethingWithLambdaClass(
    private val lambdaListener: (String, String) -> Unit
) {

    init {
        lambdaListener("hai", "hello")
    }

}


//-----------------------------using interfaces instead of lambdas------------------------------------------

class SumClass {

    fun funSum(a: Int, b: Int, sumInterface: SumInterface) {
        val sum = a + b
        sumInterface.executeSum(sum)
    }
}

interface SumInterface {
    fun executeSum(sum: Int)
}

//----------------kotlin scope functions let, apply, run, with and also--------------------------------------
class Cart {
    var itemCount = 2
    var itemName = "Apple Air Pod"

    fun addTwoNumbers() {
        var sum = 5 + 2
    }
}

//---------------------inline functions in kotlin---------------------------------------------------

//inline functions gets copied to the call site during compilation, significant performance improvement when used with higher order functions
inline fun myInlineFunction(a: String, listener: (Int) -> Unit) {
    print("inside my inline function")
}

//-----------------------kotlin infix and tailrec functions------------------------------------------------

/*infix functions are extension functions with a single parameter, so all infix functions are extension functions but not all extension functions are
infix functions.
use: can call an infix function with the class object without using a dot or parentheses across the parameter. it increases readability


 kotlin tailrec keyword can be used to avoid performance overhead due to stack overflow error caused by too many recursion calls.
  when used this keyword, recursive  functions won't run out of stack memory(since these recursions are handled separately)*/


//infix function
infix fun String.myInfixFun(a: String) { /*do some operation*/
} // extension function with single parameter

var myString = "my string"

//now we can call the function like this
var result = myString myInfixFun "is this" // same as myString.myInfixFun("is this")


//tailrec keyword
tailrec fun myRecursiveFunction(cubeValue: Long) { // without the tailrec keyword this operation will throw a stackoverflow error
    var conditionValue = 200L
    print(cubeValue * cubeValue * cubeValue * cubeValue)
    if (conditionValue == 0L) return
    conditionValue--
    return myRecursiveFunction(cubeValue = conditionValue)
}

//--------------------------------reified keyword----------------------------------------------------------------------
inline fun <reified T> genericTaker(/*classType: Class<T>,*/someValue: T) { // since generic is 'reified',we get the argument type inside the block without passing the generic class itself
    print("the passed generic type is : ${T::class.java}") // if reified is not used it will throw "Cannot use 'T' as reified type parameter. Use a class instead."
//print("the passed generic type is : $classType") // if reified is not used, we have to pass the generic class to find out the generic type
    // in short you can get the generic type(class type) from the generic parameter itself when reified keyword is used
}

//---------------------------------Handler-----------------------------------------------------------------------]
fun testHandlers(firstNumber: Int, secondNumber: Int) {

//    var handler = android.os.Handler

}


//-------------------------------Effect Handlers-----------------------------------------------------------------
//Side Effect : any change to the state that doesn't happen in the context of the composable
@Composable
fun sampleComposable() {

    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, Uri.parse(""))
    }


    var currentContext = LocalContext.current
    LocalContext.current.showKeyboard()       // this code will get executed on every state change
    Text("Hello world")
    //Preventing side effects in two ways, rewriting the above code
    //1. Launched Effect
    LaunchedEffect(key1 = true) {   // it gives a coroutine scope to work in.
        //key will determine when the coroutine in the launchedEffect block will be launched and cancelled
        //here since hardcoded value 'true' is passed it will get executed only on the first composition
        //we can set a custom value so that we can have more control over it (eg: if you want the keyboard to be shown when a particular tab is clicked)
        currentContext.showKeyboard() // this way, it won't get executed on every composition
    }

    //2.Side Effect
    SideEffect {
        //it doesn't have any parameters
        //
        //do something
    }

    //3.Disposable Effect
    DisposableEffect(key1 = true) {
        //used when csll
        val listener = AudioRouting.OnRoutingChangedListener {
            //Do something
        }
        mediaPlayer.addOnRoutingChangedListener(
            listener,
            android.os.Handler(Looper.getMainLooper()) { true }
        )

        onDispose {
            mediaPlayer.removeOnRoutingChangedListener(listener)
        }
    }

    //4.rememberCoroutineScope


}

fun Context.showKeyboard() {

}


//Side Effect


//---------------------------------label alternative in kotlin---------------------------------------------------
fun labelAlternativeInKotlin(args: Array<String>) {
    Task@ for (i in 0..9) {               // denote any label with its name followed by @
        // when calling use the opposite syntax @ followed by label name
        if (i == 8) {
            continue@Task
        }
        println(".......$i")
    }
}


//----------------------------------------------------------Tips--------------------------------------------------------------
// Live data is asynchronous by default

//Supporting multiple screen sizes in android
//xml
// always recommended to use constraint layouts
// add guidelines (right click - helpers - add vertical guidelines) (view) and set constraint guide percent to a particular percentage (eg: 0.35) (code)
//constraint dimension ration width:height (eg: 1:1) (code)

// (view) constraint to the top and bottom and select  all views and  right click - chains - vertical chain

// qualifiers - view - create others -  select available qualifiers - create layouts specifically for them,
//eg: select 'smallest screen width' click >> enter value 320, 480,720...etc (will create an additional layout file for it)
// for landscape mode select the landscape qualifier(you can select multiple qualifiers)


//------------------------------------Compose essentials--------------------------------------------------

// rememberSavable - retain states over configuration changes
//unidirectional data flow- state goes down and events go up
// state hoisting- moving states out of the ui composable to a view model or a calling composable function


//('a'..'z').foreachIndexed{ index, value ->  }. foreachIndexed will give you index along with value
// list.shuffle to shuffle the list

//list.sortedby, sortedbyAscending, sortedbyDescending

//livedata.switchMap( changedValue -> ) will trigger when live data changes

//use "cachedIn(viewModelScope)" in view model inorder to cache (especially while paging, use this to get already loaded data from the memory on configuration changes like screen rotation)

