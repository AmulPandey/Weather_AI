package com.example.weatherai.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherai.util.LocationPreset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityChips(
    cities: List<LocationPreset>,
    selected: LocationPreset,
    onSelect: (LocationPreset) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        items(cities) { city ->
            FilterChip(
                selected = city == selected,
                onClick = { onSelect(city) },
                label = { Text(city.city) }
            )
        }
    }
}