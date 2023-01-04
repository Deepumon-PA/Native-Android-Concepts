package com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{ // to access it in room hilt module
        const val DATABASE_NAME = "myAndroidApp_DB"
    }
}