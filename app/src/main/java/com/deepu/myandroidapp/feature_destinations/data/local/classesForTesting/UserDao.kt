package com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM myAndroidApp_DB WHERE userId == :passedId")
    suspend fun getUserById(passedId: Long): User

    @Query("DELETE FROM myAndroidApp_DB WHERE userId == :passedId")
    suspend fun deleteUser(passedId: Long)

    @Query("DELETE FROM myAndroidApp_DB")
    suspend fun clearTable()

    @Query("SELECT * FROM myAndroidApp_DB")
    fun getAllUsers(): LiveData<List<User>> // not a suspend function since it returns live data which is asynchronous by default

}