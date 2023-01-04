package com.deepu.myandroidapp.feature_destinations.data.remote

import com.deepu.myandroidapp.feature_destinations.data.remote.dto.CountriesDTO

interface CountriesKtorAPI {
    suspend fun getCountries(): List<CountriesDTO>

}