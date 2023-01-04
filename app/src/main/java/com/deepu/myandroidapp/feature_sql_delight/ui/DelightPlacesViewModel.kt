package com.deepu.myandroidapp.feature_sql_delight.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepu.myandroidapp.feature_sql_delight.data.PlacesDelightDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sqldelightdatabase.placesdb.PlaceEntity
import javax.inject.Inject

@HiltViewModel
class DelightPlacesViewModel @Inject constructor(
    private val placesDataSource: PlacesDelightDataSource
) : ViewModel() {

    val places = placesDataSource.getAllPlacesSQLDelight()

    var placeDetails by mutableStateOf<PlaceEntity?>(null)
        private set    // making the setter for this var private so that it won't be accessible anywhere else

    var placeNameText by mutableStateOf("")
        private set

    var countryNameText by mutableStateOf("")
        private set

    //when you click the delete button on an item in the list
    fun onDeletePlaceClick(id: Long) {
        viewModelScope.launch {
            placesDataSource.deletePlaceById(id)
        }
    }

    //when you click the tick button after to add an item
    fun onInsertPlaceClick() {
        if (placeNameText.isBlank() || countryNameText.isBlank())
            return

        viewModelScope.launch {
            placesDataSource.insertPlace(placeNameText, countryNameText)
        }

    }

    //for showing details on a dialog when an item is clicked
    fun onGetPlaceById(id: Long) {
        viewModelScope.launch {
            placesDataSource.getPlaceByIdSQLDelight(id)
        }
    }

    //when you type on the place name field
    fun onPlaceNameChange(placeName: String) {
        placeNameText = placeName
    }

    //when you type on the country name field
    fun onCountryNameChange(countryName: String) {
        countryNameText = countryName
    }

    //when you dismiss the dialog
    fun onPlaceDetailsDialogDismiss() {
        placeDetails = null
    }
}