package com.example.weatherai

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherai.ui.screens.WeatherScreen
import com.example.weatherai.ui.theme.WeatherAiTheme
import com.example.weatherai.ui.viewmodel.WeatherViewModel
import com.example.weatherai.ui.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as WeatherApplication

        setContent {
            WeatherAiTheme {
                val viewModel: WeatherViewModel = viewModel(
                    factory = WeatherViewModelFactory(app.weatherRepository)
                )
                WeatherScreen(viewModel = viewModel)
            }
        }
    }
}