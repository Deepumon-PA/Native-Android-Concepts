package com.deepu.myandroidapp.feature_sql_delight.data

import com.deepu.myandroidapp.PlacesDataBase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import sqldelightdatabase.placesdb.PlaceEntity

class PlacesDelightDataSourceImpl(
    db: PlacesDataBase
) : PlacesDelightDataSource{

    private val queries = db.placeEntityQueries

    override suspend fun getPlaceByIdSQLDelight(id: Long): PlaceEntity? {
        return withContext(Dispatchers.IO){
            queries.getPlaceById(id).executeAsOneOrNull() //executeAsOneOrNull will handle null as well instead executeAsOne won't
        }
    }

    override fun getAllPlacesSQLDelight(): Flow<List<PlaceEntity>> {
        return queries.getAllPlaces().asFlow().mapToList()

    }

    override suspend fun deletePlaceById(id: Long) {
        withContext(Dispatchers.IO){
            queries.deletePlaceById(id)
        }
    }

    override suspend fun insertPlace(firstName: String, lastName: String, id: Long?) {
        withContext(Dispatchers.IO){
            queries.insertPlace(id, firstName, lastName)
        }
    }
}
