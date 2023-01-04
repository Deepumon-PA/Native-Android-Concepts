package com.deepu.myandroidapp.feature_destinations.data.remote

import com.deepu.myandroidapp.feature_destinations.data.remote.dto.CountriesDTO
import retrofit2.http.GET

interface CountriesAPI {
    @GET("/tourismDestinations")
    suspend fun getDestinations(): List<CountriesDTO>
}