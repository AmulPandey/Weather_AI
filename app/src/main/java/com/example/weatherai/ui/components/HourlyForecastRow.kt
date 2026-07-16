package com.example.weatherai.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherai.data.remote.dto.HourlyDto
import com.example.weatherai.util.WeatherCodeMapper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastRow(hourly: List<HourlyDto>, modifier: Modifier = Modifier) {
    if (hourly.isEmpty()) return

    Column(modifier = modifier) {
        Text(
            text = "Hourly",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hourly.take(24)) { hour ->
                HourlyItem(hour)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HourlyItem(hour: HourlyDto) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(formatHour(hour.time), style = MaterialTheme.typography.labelSmall)
            Spacer(Modifier.height(6.dp))
            Text(WeatherCodeMapper.emoji(hour.weatherCode), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text("${hour.temp.toInt()}°", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatHour(isoTime: String): String = try {
    LocalDateTime.parse(isoTime).format(DateTimeFormatter.ofPattern("h a"))
} catch (e: Exception) {
    isoTime
}