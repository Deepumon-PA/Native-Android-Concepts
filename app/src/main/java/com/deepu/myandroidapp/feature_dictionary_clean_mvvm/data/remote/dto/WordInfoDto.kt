package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.remote.dto

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.entities.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {

        return WordInfoEntity(
            meanings = this.meanings.map { it.toMeaning() },
            origin = this.origin,
            phonetic = this.phonetic,
            word = this.word
        )
    }
}