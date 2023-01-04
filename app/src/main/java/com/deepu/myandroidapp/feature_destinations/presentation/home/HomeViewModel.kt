package com.deepu.myandroidapp.feature_destinations.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepu.myandroidapp.common.Resource
import com.deepu.myandroidapp.feature_destinations.domain.use_case.get_countries.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        getCountries()
    }

    private fun getCountries() {

        getCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CountriesState(countries = result.data ?: emptyList(), isLoading = false)
                }

                is Resource.Error<*> -> {
                    _state.value = CountriesState(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = CountriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}



// saveStateHandle: a bundle, contains information about the saved state, useful to restore app from process death
// also helps to pass nav parameters
