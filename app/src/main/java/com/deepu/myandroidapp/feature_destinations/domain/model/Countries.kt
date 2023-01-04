package com.deepu.myandroidapp.feature_destinations.domain.model

import com.deepu.myandroidapp.feature_destinations.data.models.TopSight

data class Countries(
    var countryId: Long,
    val countryName: String,
    val countryCapital: String,
    val continent: String,
    val countryImage: String,
    val description: String,
    val topSights: MutableList<TopSight>
)


