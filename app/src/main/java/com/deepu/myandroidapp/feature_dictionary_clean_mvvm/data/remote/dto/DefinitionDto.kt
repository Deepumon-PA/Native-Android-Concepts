package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.remote.dto

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
) {
    fun toDefinition(): Definition {

        return Definition(
            antonyms = this.antonyms,
            definition = this.definition,
            example = this.example,
            synonyms = this.synonyms
        )

    }
}

