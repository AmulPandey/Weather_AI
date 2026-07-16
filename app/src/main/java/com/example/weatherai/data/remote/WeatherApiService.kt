package com.example.weatherai.data.remote

import com.example.weatherai.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("days") days: Int = 7,
        @Query("ai") ai: Boolean = true,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
    ): WeatherResponseDto
}