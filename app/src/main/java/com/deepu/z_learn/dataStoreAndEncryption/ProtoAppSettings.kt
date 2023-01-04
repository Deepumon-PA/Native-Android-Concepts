package com.deepu.z_learn.dataStoreAndEncryption

import kotlinx.serialization.Serializable

@Serializable
data class ProtoAppSettings(
    val language: Language = Language.ENGLISH,
    val knownLocations: List<Location> = emptyList()
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

enum class Language {
    ENGLISH,
    GERMAN
}