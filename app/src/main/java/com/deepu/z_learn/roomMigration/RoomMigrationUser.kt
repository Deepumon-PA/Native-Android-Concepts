package com.deepu.z_learn.roomMigration

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class RoomMigrationUser(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val userName: String

)