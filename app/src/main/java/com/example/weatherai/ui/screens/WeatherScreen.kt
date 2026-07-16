package com.example.weatherai.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherai.ui.components.*
import com.example.weatherai.ui.viewmodel.WeatherViewModel
import com.example.weatherai.util.LocationPresets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            Spacer(Modifier.height(8.dp))
            CityChips(
                cities = LocationPresets.list,
                selected = uiState.selectedLocation,
                onSelect = viewModel::onLocationSelected,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            when {
                uiState.isLoading && uiState.weather == null -> FullScreenLoading()

                uiState.errorMessage != null && uiState.weather == null -> FullScreenError(
                    message = uiState.errorMessage ?: "Something went wrong",
                    onRetry = viewModel::retry
                )

                uiState.weather != null -> {
                    val weather = uiState.weather!!
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        item {
                            CurrentWeatherCard(
                                cityName = uiState.selectedLocation.city,
                                current = weather.current,
                                units = weather.units,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }

                        weather.aiSummary?.let { summary ->
                            item { AiSummaryCard(summary) }
                        }

                        weather.hourly?.let { hourly ->
                            item { HourlyForecastRow(hourly) }
                        }

                        item { DailyForecastList(weather.daily) }
                    }
                }
            }
        }
    }
}