package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.utils.JsonParser
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter // to let room know that this type converter is custom provided
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<List<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(obj: List<Meaning>): String {
        return jsonParser.toJson(
            obj,
            object : TypeToken<List<Meaning>>() {}.type

        ) ?: "[]"
    }
}