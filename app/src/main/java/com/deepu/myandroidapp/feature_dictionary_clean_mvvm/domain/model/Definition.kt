package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
)
