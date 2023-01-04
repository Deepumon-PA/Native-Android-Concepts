package com.deepu.myandroidapp.feature_destinations.data.local.entity

import com.deepu.myandroidapp.feature_destinations.data.models.TopSight

data class CountriesEntity(
    val countryId: Long,
    val countryName: String,
    val countryCapital: String,
    val continent: String,
    val countryImage: String,
    val description: String,
    val topSights: MutableList<TopSight>
)