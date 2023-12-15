package com.teldar.weatherapp.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val location: Location,
    val forecast: Forecast,
    val current: CurrentWeather,
)

