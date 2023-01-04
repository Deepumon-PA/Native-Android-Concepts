package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.utils

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJson(json: String, type: Type): T? // convert json to the specified type

    fun<T> toJson(obj: T, type: Type): String? // convert given object to the specified type
}

