package com.teldar.weatherapp.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.teldar.weatherapp.models.WeatherResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class WeatherService(private val _apiKey: String) {

    private val _retrofit: Retrofit
    private val _weatherApiClient: WeatherApiClient

    init {
        val contentType = MediaType.parse("application/json")
            ?: throw Exception("Cannot convert")
        val json = Json { ignoreUnknownKeys = true }
        _retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        _weatherApiClient = _retrofit.create(WeatherApiClient::class.java)
    }


    suspend fun getWeather(city: String): Result<WeatherResponse> {
        val response = try {
            Result.success(_weatherApiClient.getWeatherForecast(city, 7, _apiKey))
        } catch (t: Throwable) {
            Result.failure(t)
        }
        return response
    }
}