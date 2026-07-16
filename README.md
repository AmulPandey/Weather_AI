# 🌦️ Weather AI

A modern Android weather app built with **Jetpack Compose** and **MVVM**, powered by the [Weather AI API](https://weather-ai.co) — featuring real-time conditions, multi-day forecasts, and AI-generated weather summaries.

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-24-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

---

## ✨ Features

- 📍 Current conditions for preset cities (Nairobi, London, New York, Tokyo, Delhi, Sydney)
- 📅 Multi-day forecast (up to 7 days on the free API tier)
- ⏱️ Hourly forecast breakdown
- 🤖 AI-generated natural-language weather summaries (Gemini, via the API)
- 🎨 Clean, modern Material 3 UI built entirely in Jetpack Compose
- 🔄 Pull-to-retry error handling and loading states
- 🏗️ Clean MVVM architecture with unidirectional data flow (`StateFlow`)

## 📱 Screenshots

<p align="center">
<img alt="Overview1"  src="https://github.com/AmulPandey/Weather_AI/blob/main/app/src/main/assets/Screenshot%20(845).png">
</p>
<p align="center">
<img alt="Overview2"  src="https://github.com/AmulPandey/Weather_AI/blob/main/app/src/main/assets/Screenshot%20(846).png">
</p>

## 🏛️ Architecture
com.example.weatherai
├── data
│   ├── remote
│   │   ├── dto/              # API response models (Gson-mapped)
│   │   ├── WeatherApiService.kt
│   │   └── RetrofitClient.kt
│   └── repository/           # Repository interface + implementation
├── ui
│   ├── components/           # Reusable Compose UI pieces
│   ├── screens/               # Full screens (WeatherScreen)
│   ├── theme/                 # Color, typography, Material theme
│   └── viewmodel/             # WeatherViewModel + UiState + Factory
├── util/                      # Resource wrapper, weather code mapper, presets
├── MainActivity.kt
└── WeatherApplication.kt      # Manual DI container

**Data flow:**
WeatherScreen (Compose)
↕ observes StateFlow
WeatherViewModel
↕ calls suspend fun
WeatherRepository (interface)
↕ implemented by
WeatherRepositoryImpl
↕ calls
WeatherApiService (Retrofit)
↕ HTTP
Weather AI API (api.weather-ai.co)

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM, unidirectional data flow |
| Async | Kotlin Coroutines, StateFlow |
| Networking | Retrofit2, OkHttp, Gson |
| DI | Manual DI via `Application` class |
| Language | Kotlin |

## 🚀 Getting Started

### Prerequisites

- Android Studio (Ladybug or newer recommended)
- JDK 11+
- A free API key from [weather-ai.co](https://weather-ai.co) (Dashboard → API Keys)

### Setup

1. **Clone the repo**

```bash
   git clone https://github.com/<your-username>/<repo-name>.git
   cd <repo-name>
```

2. **Add your API key**

   Open `local.properties` (not committed to git) and add:

```properties
   WEATHER_AI_API_KEY=wai_your_key_here
```

   > ⚠️ Never commit your API key. See [`.gitignore`](.gitignore) — `local.properties` is already excluded.

3. **Open in Android Studio** and let Gradle sync.

4. **Run** on an emulator or physical device (min SDK 24).

## 🔑 API Reference

This app consumes the **Weather API** endpoint from Weather AI:
GET https://api.weather-ai.co/v1/weather
Authorization: Bearer wai_<your_api_key>

| Param | Type | Required | Description |
|---|---|---|---|
| `lat` | float | ✅ | Latitude |
| `lon` | float | ✅ | Longitude |
| `days` | integer | ❌ | Forecast days (1–7 free tier). Default: 7 |
| `ai` | boolean | ❌ | Include AI summary. Default: true |
| `units` | string | ❌ | `metric` or `imperial`. Default: metric |
| `lang` | string | ❌ | Language code for AI summary. Default: en |

Full docs: [weather-ai.co/docs](https://weather-ai.co/docs#get-weather)

## 🗺️ Roadmap

- [ ] GPS-based current location support
- [ ] Manual lat/lon search input
- [ ] Local caching / offline support
- [ ] Widget support
- [ ] Unit tests for ViewModel + Repository

## 🤝 Contributing

Contributions are welcome. Please open an issue first to discuss what you'd like to change.

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.

## 🙏 Acknowledgements

- [Weather AI](https://weather-ai.co) for the weather + AI summary API
- [Material 3](https://m3.material.io/) design guidelines

The app follows **MVVM** with a simple, explicit clean-architecture-inspired layering:
