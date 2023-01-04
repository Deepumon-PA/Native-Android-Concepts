package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.repository

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.core.util.Resource
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}