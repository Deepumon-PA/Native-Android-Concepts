package com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDataBase.DATABASE_NAME)
data class User(

    @PrimaryKey(autoGenerate = false)
    var userId: Long = 0,
    var userName: String  = "",
    var userDP: String  = "",
    var userEmail: String? = null
)