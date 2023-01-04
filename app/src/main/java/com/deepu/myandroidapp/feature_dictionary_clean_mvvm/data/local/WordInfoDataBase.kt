package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.entities.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDataBase: RoomDatabase() {
    abstract val dao: WordInfoDao
}