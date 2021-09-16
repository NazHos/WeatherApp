package com.example.weatherapp.repository

import com.example.weatherapp.api.ApiHelper
import com.example.weatherapp.model.FutureWeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
):WeatherRepository {
    override suspend fun getCurrentWeather(name:String) = apiHelper.getCurrentWeather(name)
    override suspend fun getFutureWeatherDetails(
        lat: Double,
        lon: Double
    ): Response<FutureWeatherResponse>  = apiHelper.getFutureWeatherDetails(lat, lon)
}