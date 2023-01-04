package com.deepu.z_learn.roomMigration

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.deepu.myandroidapp.R
import kotlinx.coroutines.launch


class RoomMigrationActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("isActivityActive", "inside onCreate of RoomMigrationActivity")

        val db = Room.databaseBuilder(
            applicationContext,
            RoomMigrationUserDataBase::class.java,
            "migration_users.db"
        ).build()

        lifecycleScope.launch{
            db.dao.getUsers().forEach(::println)
        }

     /*   (1..10).forEach {

            lifecycleScope.launch {

                db.dao.insertUser(

                    RoomMigrationUser(
                        email = "test@test$it.com",
                        userName = "test$it"

                    )
                )

            }
        }*/

    }
}