package com.example.weatherapp.utility

import com.example.weatherapp.model.WeatherResponse

sealed class CurrentWeatherEvent {
    class Success(val weatherResponse: WeatherResponse) : CurrentWeatherEvent()
    class Error(val message: String) : CurrentWeatherEvent()
    object Loading : CurrentWeatherEvent()
    object Empty : CurrentWeatherEvent()
}
