package com.example.weatherai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DailyDto(
    @SerializedName("date") val date: String,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("weathercode") val weatherCode: Int? = null
)