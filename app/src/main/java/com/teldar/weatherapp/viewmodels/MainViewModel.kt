package com.teldar.weatherapp.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.teldar.weatherapp.R
import com.teldar.weatherapp.models.WeatherResponse
import com.teldar.weatherapp.services.WeatherService
import kotlinx.coroutines.launch


sealed interface UiState {
    data object Success : UiState
    data object Error : UiState
    data object Loading : UiState
}

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var uiState: UiState by mutableStateOf(UiState.Error)
        private set

    var city by mutableStateOf("")

    private val _apiKey =
        getApplication<Application>().resources.getString(R.string.weather_api_key)
    var weather by mutableStateOf<WeatherResponse?>(null)

    private val _weatherService = WeatherService(_apiKey)

    fun getWeather() {
        viewModelScope.launch {
            uiState = UiState.Loading
            val result = _weatherService.getWeather(city)
            if (result.isSuccess) {
                weather = result.getOrNull()
                uiState = UiState.Success
            } else {
                uiState = UiState.Error
            }
        }
    }
}