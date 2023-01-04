package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.usecases

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.core.util.Resource
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.WordInfo
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val wordInfoRepository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return wordInfoRepository.getWordInfo(word)
    }
}