package com.example.weatherapp.repository

import com.example.weatherapp.model.FutureWeatherResponse
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCurrentWeather(name:String): Response<WeatherResponse>
    suspend fun getFutureWeatherDetails(lat:Double, lon:Double): Response<FutureWeatherResponse>

}