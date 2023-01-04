package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.di

import android.app.Application
import androidx.room.Room
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.GsonParser
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.Converters
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.WordInfoDataBase
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.remote.DictionaryApi
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.respository.WordInfoRepositoryImpl
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.repository.WordInfoRepository
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.usecases.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDataBase,
        api: DictionaryApi

    ): WordInfoRepository {
        return WordInfoRepositoryImpl(
            api,
            db.dao
        )
    }

    @Provides
    @Singleton
    fun provideWordInfoDataBase(app: Application): WordInfoDataBase {
        return Room.databaseBuilder(
            app,
            WordInfoDataBase::class.java,
            "word_db",
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}