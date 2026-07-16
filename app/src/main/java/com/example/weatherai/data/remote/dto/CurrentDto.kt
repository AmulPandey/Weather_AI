package com.example.weatherai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("time") val time: String,
    @SerializedName("interval") val interval: Int,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("winddirection") val windDirection: Int,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("weathercode") val weatherCode: Int
)