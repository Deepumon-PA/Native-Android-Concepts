package com.deepu.myandroidapp.feature_destinations.domain.repository

import com.deepu.myandroidapp.feature_destinations.data.remote.dto.CountriesDTO

interface CountriesRepository {
    suspend  fun getDestinations(): List<CountriesDTO>
}