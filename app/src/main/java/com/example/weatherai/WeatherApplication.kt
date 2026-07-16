package com.example.weatherai

import android.app.Application
import com.example.weatherai.data.remote.RetrofitClient
import com.example.weatherai.data.repository.WeatherRepository
import com.example.weatherai.data.repository.WeatherRepositoryImpl

class WeatherApplication : Application() {

    lateinit var weatherRepository: WeatherRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val apiKey = BuildConfig.WEATHER_AI_API_KEY
        check(apiKey.isNotBlank()) {
            "Missing WEATHER_AI_API_KEY — add it to local.properties"
        }
        val apiService = RetrofitClient.create(apiKey = apiKey)
        weatherRepository = WeatherRepositoryImpl(api = apiService)
    }
}