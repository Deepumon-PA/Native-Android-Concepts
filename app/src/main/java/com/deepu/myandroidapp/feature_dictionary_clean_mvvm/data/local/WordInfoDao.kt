package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local

import androidx.room.*
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.entities.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(wordInfos: List<WordInfoEntity>)

    @Query("DELETE FROM WordInfoEntity WHERE word IN (:words)")
    suspend fun deleteWordInfo(words: List<String>)

    @Query("SELECT * FROM WordInfoEntity WHERE word LIKE '%' || :word || '%'") //% means anything, || used to combine or concatenate strings in ROOM Database
    suspend fun getWordInfos(word: String): List<WordInfoEntity>

}