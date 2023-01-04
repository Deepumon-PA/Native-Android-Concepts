package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)
