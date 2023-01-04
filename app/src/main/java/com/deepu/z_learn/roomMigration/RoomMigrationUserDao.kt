package com.deepu.z_learn.roomMigration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting.User

@Dao
interface RoomMigrationUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: RoomMigrationUser)

    @Query("SELECT * FROM RoomMigrationUser")
    suspend fun getUsers(): List<RoomMigrationUser>
}