package com.example.weatherai.data.repository

import com.example.weatherai.data.remote.WeatherApiService
import com.example.weatherai.data.remote.dto.WeatherResponseDto
import com.example.weatherai.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import retrofit2.HttpException

class WeatherRepositoryImpl(
    private val api: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
        days: Int,
        units: String
    ): Resource<WeatherResponseDto> = withContext(Dispatchers.IO) {
        try {
            val response = api.getWeather(lat = lat, lon = lon, days = days, units = units)
            Resource.Success(response)
        } catch (e: HttpException) {
            val msg = when (e.code()) {
                401 -> "Invalid or missing API key"
                403 -> "Your plan doesn't include this feature"
                429 -> "Monthly quota exceeded"
                else -> e.message() ?: "Server error (${e.code()})"
            }
            Resource.Error(msg)
        } catch (e: IOException) {
            Resource.Error("Couldn't reach the server. Check your connection.")
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Something went wrong")
        }
    }
}