package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.remote.dto

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.Definition
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = this.definitions.map { it.toDefinition() },
            partOfSpeech = this.partOfSpeech
        )
    }
}