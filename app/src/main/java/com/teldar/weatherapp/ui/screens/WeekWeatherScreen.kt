package com.teldar.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teldar.weatherapp.ui.components.WeatherCard
import com.teldar.weatherapp.ui.theme.WeatherAppTheme
import com.teldar.weatherapp.viewmodels.MainViewModel
import com.teldar.weatherapp.viewmodels.UiState

@Composable
fun WeekWeatherScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {

    val weatherResponse = viewModel.weather

    if (viewModel.uiState == UiState.Loading) {
        LoadingScreen()
        return
    }

    if (weatherResponse == null || viewModel.uiState == UiState.Error) {
        ErrorScreen(message = "Ошибка при получении погоды!")
        return
    }
    val isDay = (viewModel.weather?.current?.isDay ?: 0) != 0
    WeatherAppTheme(darkTheme = !isDay) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            items(viewModel.weather?.forecast?.forecastDays ?: listOf()) {
                WeatherCard(
                    forecast = it,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)
                )
            }
        }
    }
}