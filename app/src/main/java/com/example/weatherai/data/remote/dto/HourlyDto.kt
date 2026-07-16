package com.example.weatherai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HourlyDto(
    @SerializedName("time") val time: String,
    @SerializedName("temp") val temp: Double,
    @SerializedName("precipitation") val precipitation: Double,
    @SerializedName("weathercode") val weatherCode: Int
)