package com.deepu.myandroidapp.feature_destinations.presentation.home

import com.deepu.myandroidapp.feature_destinations.domain.model.Countries

data class CountriesState(
    var isLoading: Boolean = false,
    val countries: List<Countries> = emptyList(),
    val error: String = ""
)
