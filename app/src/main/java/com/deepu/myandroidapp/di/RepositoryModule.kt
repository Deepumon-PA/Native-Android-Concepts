package com.deepu.myandroidapp.di

import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesAPI
import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesKtorAPI
import com.deepu.myandroidapp.feature_destinations.data.repository.CountriesRepositoryImpl
import com.deepu.myandroidapp.feature_destinations.domain.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

 /*   @Provides
    @Singleton
    fun provideCountriesRepository(countriesAPI:CountriesAPI): CountriesRepository{
        return CountriesRepositoryImpl(countriesAPI = countriesAPI)
    }
*/
    @Singleton
    @Provides
    fun provideCountriesRepository(countriesAPI:CountriesAPI, countriesKtorAPI: CountriesKtorAPI): CountriesRepository{
        return CountriesRepositoryImpl(countriesAPI = countriesAPI, countriesKtorAPI = countriesKtorAPI)
    }

}