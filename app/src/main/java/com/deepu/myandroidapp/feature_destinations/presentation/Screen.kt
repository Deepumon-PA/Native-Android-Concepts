package com.deepu.myandroidapp.feature_destinations.presentation

sealed class Screen(val route: String) {

    object LoginScreen: Screen("login_screen")
    object HomeScreen: Screen("home_screen")
    object CountryOpenedScreen: Screen("country_details")

}