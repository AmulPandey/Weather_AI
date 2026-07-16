package com.example.weatherai.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
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
import com.example.weatherai.data.remote.dto.DailyDto
import com.example.weatherai.util.WeatherCodeMapper
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecastList(daily: List<DailyDto>, modifier: Modifier = Modifier) {
    if (daily.isEmpty()) return

    Column(modifier = modifier) {
        Text(
            text = "Next ${daily.size} days",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                daily.forEach { day -> DailyRow(day) }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DailyRow(day: DailyDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formatDayLabel(day.date),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = WeatherCodeMapper.emoji(day.weatherCode ?: 0),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = "${day.tempMin.toInt()}° / ${day.tempMax.toInt()}°",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatDayLabel(dateStr: String): String = try {
    val date = LocalDate.parse(dateStr)
    if (date == LocalDate.now()) "Today"
    else date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
} catch (e: Exception) {
    dateStr
}