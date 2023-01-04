package com.deepu.myandroidapp.di

import android.app.Application
import com.deepu.myandroidapp.PlacesDataBase
import com.deepu.myandroidapp.feature_sql_delight.data.PlacesDelightDataSource
import com.deepu.myandroidapp.feature_sql_delight.data.PlacesDelightDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Hilt module for SQLDelight places module

@Module
@InstallIn(SingletonComponent::class)
object DelightPlacesModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = PlacesDataBase.Schema,
            context = app,
            name = "person.db"
        )
    }


    @Provides
    @Singleton
    fun providePersonDataSource(driver: SqlDriver): PlacesDelightDataSource {
        return PlacesDelightDataSourceImpl(PlacesDataBase(driver))
    }

}