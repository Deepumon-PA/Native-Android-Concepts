package com.deepu.myandroidapp.feature_sql_delight.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import sqldelightdatabase.placesdb.PlaceEntity

private const val TAG = "PlaceListScreenLog"

@Composable
fun PlacesListScreen(
    viewModel: DelightPlacesViewModel = hiltViewModel()
) {

    val places = viewModel.places.collectAsState(initial = emptyList()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(places) { place ->

                    PlaceItem(
                        modifier = Modifier.fillMaxWidth(),
                        placeEntity = place,
                        onItemClick = {
                            viewModel.onGetPlaceById(place.id)
                        },
                        onDeleteClick = {
                            viewModel.onDeletePlaceClick(place.id)
                        }
                    )

                    viewModel.placeDetails?.let { details ->
                        Dialog(onDismissRequest = viewModel::onPlaceDetailsDialogDismiss) {
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "${details.placeName}  ${details.CountryName}", color = Color.Red)
                            }
                        }
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top

            ) {

                TextField(
                    value = viewModel.placeNameText,
                    onValueChange = viewModel::onPlaceNameChange,
                    placeholder = {
                        Text(text = "Place", color = Color.Black)
                    },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = viewModel.countryNameText,
                    onValueChange = viewModel::onCountryNameChange,
                    placeholder = {
                        Text(text = "Country", color = Color.Black)
                    },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        viewModel.onInsertPlaceClick()
                    }) {

                    Icon(
                        imageVector = Icons.Outlined.Done,
                        contentDescription = "Add Entry",
                        tint = Color.Green
                    )
                }
            }

        }

    }
}


//place item is a single row available for items and get with new things as well
@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    placeEntity: PlaceEntity,
    onItemClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable {
                onItemClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = placeEntity.placeName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        IconButton(onClick = {
            onDeleteClick()
        }) {

            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "delete place",
                tint = Color.Gray
            )
        }
    }
}

//what is an icon and how the outlined icon is created and provided is a different for once and for all


