package com.deepu.myandroidapp.feature_destinations.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TopSight(
    val placePopulation: String,
    val rating: String,
    val sightImageUrl: String,
    val sightName: String,
    val sightPlace: String,
    val description: String
)