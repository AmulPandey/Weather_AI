package com.example.weatherai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherai.data.remote.dto.CurrentDto
import com.example.weatherai.ui.theme.SkyBlueBottom
import com.example.weatherai.ui.theme.SkyBlueTop
import com.example.weatherai.util.WeatherCodeMapper

@Composable
fun CurrentWeatherCard(
    cityName: String,
    current: CurrentDto,
    units: String,
    modifier: Modifier = Modifier
) {
    val unitSymbol = if (units == "imperial") "°F" else "°C"
    val isDay = current.isDay == 1

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(listOf(SkyBlueTop, SkyBlueBottom))
            )
            .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(cityName, style = MaterialTheme.typography.titleMedium, color = androidx.compose.ui.graphics.Color.White)

            Spacer(Modifier.height(8.dp))

            Text(
                text = WeatherCodeMapper.emoji(current.weatherCode, isDay),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "${current.temperature.toInt()}$unitSymbol",
                style = MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = WeatherCodeMapper.description(current.weatherCode),
                style = MaterialTheme.typography.bodyLarge,
                color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f)
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                WeatherStat(label = "Wind", value = "${current.windSpeed} km/h")
                WeatherStat(label = "Direction", value = "${current.windDirection}°")
            }
        }
    }
}

@Composable
private fun WeatherStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.bodyLarge, color = androidx.compose.ui.graphics.Color.White, fontWeight = FontWeight.SemiBold)
        Text(label, style = MaterialTheme.typography.labelSmall, color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f))
    }
}

