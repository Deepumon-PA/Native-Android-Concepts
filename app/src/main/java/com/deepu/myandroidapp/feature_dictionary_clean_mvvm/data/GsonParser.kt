package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data

import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.utils.JsonParser
import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(
    private val gson: Gson
): JsonParser {

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }
}