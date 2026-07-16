package com.example.weatherai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherai.data.remote.dto.WeatherResponseDto
import com.example.weatherai.data.repository.WeatherRepository
import com.example.weatherai.util.LocationPreset
import com.example.weatherai.util.LocationPresets
import com.example.weatherai.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WeatherUiState(
    val selectedLocation: LocationPreset = LocationPresets.list.first(),
    val weather: WeatherResponseDto? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        fetchWeather(_uiState.value.selectedLocation)
    }

    fun onLocationSelected(location: LocationPreset) {
        _uiState.update { it.copy(selectedLocation = location) }
        fetchWeather(location)
    }

    fun retry() = fetchWeather(_uiState.value.selectedLocation)

    private fun fetchWeather(location: LocationPreset) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = repository.getWeather(lat = location.lat, lon = location.lon)) {
                is Resource.Success -> _uiState.update {
                    it.copy(isLoading = false, weather = result.data, errorMessage = null)
                }
                is Resource.Error -> _uiState.update {
                    it.copy(isLoading = false, errorMessage = result.message)
                }
                else -> Unit
            }
        }
    }
}