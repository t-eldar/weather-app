package com.teldar.weatherapp.services

import com.teldar.weatherapp.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {
    @GET("v1/forecast.json")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("key") apiKey: String
    ): WeatherResponse
}