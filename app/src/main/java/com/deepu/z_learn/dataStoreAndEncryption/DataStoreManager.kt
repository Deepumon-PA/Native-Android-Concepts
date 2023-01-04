package com.deepu.z_learn

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


//-----------------Difference between const and val--------------------------------------
//const and val both are used to store constant immutable values
//but val can be initialized at runtime as well, so you can assign a val variable to a function or a class

//const val companyName = getCompanyName() // will not work
//val companyName = getCompanyName() // this will work

//---------------Android DataStore-------------------------------------------------------

//DataStore - jetpack library for asynchronous data storage, can be a replacement for shared preferences

//asynchronous with kotlin coroutine and flow support
//thread safe and non blocking in contrast to shared preferences
//ideal for storing preferences or application data

//provides two implementations
//1.preferences DataStore: to store key value pairs:
// doesn't need a predefined schema and is not type safe

//2.Proto DataStore: Stores typed objects backed by protocol buffers
//ie, stores data as an instance of a custom data type, requires to define a schema using protocol buffers, and is type safe

//Differences between dataStore preferences and proto dataStore



class DataStoreManager(private val context: Context) {  // or use a an object to reduce the overhead of creating class instances

    //write data into preferences dataStore
    suspend fun writeIntoDataStore() {
        context.dataStore.edit { mutablePreferences ->
            //read preference value
            val currentSecretKey = mutablePreferences[SECRET_INTEGER_KEY] ?: 0
            //write into preference
            mutablePreferences[SECRET_INTEGER_KEY] = currentSecretKey + 1
        }
    }

    suspend fun writeEncryptedText(secretText: String) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[ENCRYPTED_TEXT] = secretText
        }
    }

    suspend fun getEncryptedText(): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ENCRYPTED_TEXT]?:""
    }

    //Read data from preferences dataStore
    //datastore.data will return a flow
    val secretInteger: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[SECRET_INTEGER_KEY] ?: 0
    }.catch {  // Handling exceptions in preferences datastore
        //Handle the exception here
    }


    //Proto Datastore




    companion object {
        private const val DATA_STORE_NAME = "MyDataStore"

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

        //now create Your key to store a specific data type
        private val SECRET_INTEGER_KEY = intPreferencesKey("MySecretIntegerKey")
        private val ENCRYPTED_TEXT = stringPreferencesKey("EncryptedText")
    }
}

