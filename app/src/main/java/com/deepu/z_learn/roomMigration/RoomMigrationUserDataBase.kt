package com.deepu.z_learn.roomMigration

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomMigrationUser::class],
    version = 1
)
abstract class RoomMigrationUserDataBase : RoomDatabase() {

    abstract val dao: RoomMigrationUserDao
}