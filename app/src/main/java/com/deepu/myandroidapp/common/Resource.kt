package com.deepu.myandroidapp.common

// do you know one advantage of sealed class is you are already defining all the possible states here itself, so no need of an else case when used in branching blocks
sealed class Resource<out R> {
                  //accepts a generic type parameter as argument
    data class Success<out T>(val data: T, val message: String): Resource<T>() //
                                             //returns the same sealed resource class with
    data class Error<out T>(val exception: T, val message: String): Resource<Nothing>()

    object Loading: Resource<Nothing>()
}
