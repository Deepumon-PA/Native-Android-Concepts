package com.deepu.myandroidapp.di

import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesKtorAPI
import com.deepu.myandroidapp.feature_destinations.data.remote.CountriesKtorAPIImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    val TIME_OUT = 60000

    private const val TAG = "KtorModule"

    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient {

        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    /* kotlinx.serialization.json.Json {
                         prettyPrint = true,
                         isLenient = true,
                         ignoreUnknownKeys = true
                     }*/
                )
                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                level = LogLevel.ALL
            }
/*
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d(TAG, "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }*/

        }
    }

    @Singleton
    @Provides
    fun provideCountriesKtorAPI(ktorHttpClient: HttpClient): CountriesKtorAPI {
        return CountriesKtorAPIImpl(ktorHttpClient = ktorHttpClient)
    }


}