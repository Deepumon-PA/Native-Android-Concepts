package com.deepu.myandroidapp.feature_destinations.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries
import com.deepu.myandroidapp.feature_destinations.presentation.home.components.CountriesListItem
import com.deepu.myandroidapp.feature_destinations.presentation.home.components.TopSightListItem
import com.deepu.myandroidapp.ui.theme.DarkByzantineBlue
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {

    val state = homeViewModel.state.value

    var scrollState = rememberLazyListState()

    var coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    SideEffect { }// add all the non composable code or code that doesn't depend on composable here

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkByzantineBlue)
    ) {
        state.isLoading = false
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(20.dp)

                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = "fav_icon",
                        modifier = Modifier.align(Alignment.TopEnd),
                        tint = Color.White
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .wrapContentHeight()
                                .wrapContentWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_pic),
                                contentDescription = "User_DP",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(percent = 50))
                                    .height(50.dp)
                                    .width(50.dp)
                                    .padding(5.dp),
                                alignment = Alignment.CenterStart
                            )
                        }


                        Text(text = "Hi user, welcome back", style = MaterialTheme.typography.body1)
                    }

                }



                Text(
                    text = "Recently viewed",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(start = 20.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 10.dp)
                ) {
                    items(state.countries) { item: Countries ->
                        TopSightListItem(topSight = item)
                    }
                }

                Text(
                    text = "Countries",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            stickyHeader {
                Modifier.padding(20.dp)
                SearchBar(hint = "Search Countries/Destinations")
            }

            items(state.countries) { country ->
                CountriesListItem(countries = country, onItemClick = {
                    coroutineScope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar(it.countryName)
                        Toast.makeText(context, "this is a toast ${it.countryName}", Toast.LENGTH_SHORT).show()
                    }
//                    navController.navigate(Screen.HomeScreen.route)
                })
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error, style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }

}

@Composable
fun SearchBar(
    hint: String = "",
) {

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    var isHintVisible by remember {
        mutableStateOf(false)
    }
    searchQuery = TextFieldValue(hint)

    Box(
        modifier = Modifier
            .padding(20.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color.White, CircleShape), contentAlignment = Alignment.CenterStart
    ) {


        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "searchIcon",
                modifier = Modifier
                    .padding(10.dp),
                tint = DarkByzantineBlue,
            )

            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    if (it.text.isBlank()) {
                        searchQuery = TextFieldValue(hint)
                        isHintVisible = true
                    } else isHintVisible = false
                },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onFocusChanged {
                        if (isHintVisible) searchQuery = TextFieldValue("")
                    },
            )


        }

    }
}

/*@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = true
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}*/
