package com.example.weatherai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("units") val units: String,
    @SerializedName("days") val days: Int,
    @SerializedName("current") val current: CurrentDto,
    @SerializedName("daily") val daily: List<DailyDto>,
    @SerializedName("hourly") val hourly: List<HourlyDto>?,
    @SerializedName("ai_summary") val aiSummary: String?
)