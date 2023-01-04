package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.Meaning
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
//    val phonetics: List<Phonetic>,
    val word: String,
    @PrimaryKey val id: Int? = null
){
    fun toWordInfo(): WordInfo{
        return WordInfo(
            meanings = this.meanings,
            origin  = this.origin,
            phonetic = this.phonetic,
            word = this.word,
        )
    }
}
