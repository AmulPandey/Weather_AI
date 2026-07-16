package com.example.weatherai.util

data class LocationPreset(
    val city: String,
    val lat: Double,
    val lon: Double
)

object LocationPresets {
    val list = listOf(
        LocationPreset("Nairobi", -1.2921, 36.8219),
        LocationPreset("London", 51.5074, -0.1278),
        LocationPreset("New York", 40.7128, -74.0060),
        LocationPreset("Tokyo", 35.6762, 139.6503),
        LocationPreset("Delhi", 28.6139, 77.2090),
        LocationPreset("Sydney", -33.8688, 151.2093)
    )
}