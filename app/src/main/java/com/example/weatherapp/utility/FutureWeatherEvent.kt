package com.example.weatherapp.utility

import com.example.weatherapp.model.FutureWeatherResponse

sealed class FutureWeatherEvent {
    class Success(val futureWeatherResponse: FutureWeatherResponse) : FutureWeatherEvent()
    class Error(val message: String) : FutureWeatherEvent()
    object Loading : FutureWeatherEvent()
    object Empty : FutureWeatherEvent()
}
