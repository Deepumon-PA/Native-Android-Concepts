package com.deepu.z_learn.aptitude

import androidx.compose.ui.text.toUpperCase

//Most commonly used string handling functions

fun main(){

    val str1 = " Hello"
    val str2 = "World How are you?"

    //length
    println("length of the string 'str1' is ${str1.length}") //find the length of the string

    //get() or [index] = charAt() in java
    println("character at index 1 of 'str1' is ${str1[1]}") //returns character at the specified index

    //subString()
    println("subString from index 6 of 'str2' is ${str2.substring(6)}") // sub-string starting from the given index

    //indexOf()
    println("index of occurrence of How in 'str2' is ${str2.indexOf("How")} ") //index of first occurrence of the given string/letter

    //trim()
    println("'str1' without any leading or trailing white spaces ${str1.trim()}")

    //replace()
    println("replacing 'you' to 'we' in 'str2': ${str2.replace("you", "we")}")

    //lastIndexOf()
    println("last index of the occurrence of you in 'str2' is ${str2.lastIndexOf("you")}")

    //toLowerCase(), toUpperCase()
    println("converting 'str2' to upperCase ${str2.uppercase()}")

}

