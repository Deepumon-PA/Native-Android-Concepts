package com.deepu.myandroidapp.feature_destinations.data.remote.dto

import com.deepu.myandroidapp.feature_destinations.data.models.TopSight
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries
import kotlinx.serialization.Serializable

@Serializable
data class CountriesDTO(
    val countryId: Long,
    val countryName: String,
    val countryCapital: String,
    val continent: String,
    val countryImage: String,
    val description: String,
    val topSights: MutableList<TopSight>
)

//using kotlin extension function so that we can extend on the DestinationDTO class and access its elements on the current object
fun CountriesDTO.toCountries(): Countries {
    return Countries(
        countryId = countryId,
        countryName = countryName,
        countryCapital = countryCapital,
        continent = continent,
        countryImage = countryImage,
        description = description,
        topSights = topSights
    )
}

