package com.deepu.myandroidapp.feature_destinations.data.repository

import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesAPI
import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesKtorAPI
import com.deepu.myandroidapp.feature_destinations.data.remote.dto.CountriesDTO
import com.deepu.myandroidapp.feature_destinations.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesAPI: CountriesAPI,
    private val countriesKtorAPI: CountriesKtorAPI
) : CountriesRepository {
    override suspend fun getDestinations(): List<CountriesDTO> {
//        return countriesAPI.getDestinations()
        return countriesKtorAPI.getCountries()
    }
}