package com.deepu.myandroidapp.feature_sql_delight.data

import kotlinx.coroutines.flow.Flow
import sqldelightdatabase.placesdb.PlaceEntity

interface PlacesDelightDataSource {

    suspend fun getPlaceByIdSQLDelight(id: Long): PlaceEntity?

    fun getAllPlacesSQLDelight(): Flow<List<PlaceEntity>>

    suspend fun deletePlaceById(id: Long)

    suspend fun insertPlace(placeName: String, countryName: String, id: Long? = null)

}
