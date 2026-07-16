package com.example.weatherai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = SkyBlueTop,
    secondary = AccentOrange,
    background = SkyBlueBottom,
    surface = CardSurface,
    onSurface = TextPrimary,
    error = ErrorRed
)

private val DarkColors = darkColorScheme(
    primary = SkyBlueTop,
    secondary = AccentOrange,
    background = DarkBackground,
    surface = DarkCardSurface,
    onSurface = Color(0xFFECECEC),
    error = ErrorRed
)

@Composable
fun WeatherAiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = WeatherTypography,
        content = content
    )
}