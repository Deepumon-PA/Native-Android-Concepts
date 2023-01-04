package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.presentation

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItem: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)