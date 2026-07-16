package com.example.weatherai.data.repository

import com.example.weatherai.data.remote.dto.WeatherResponseDto
import com.example.weatherai.util.Resource

interface WeatherRepository {
    suspend fun getWeather(
        lat: Double,
        lon: Double,
        days: Int = 7,
        units: String = "metric"
    ): Resource<WeatherResponseDto>
}