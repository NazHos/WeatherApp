package com.example.weatherapp.api

import com.example.weatherapp.model.FutureWeatherResponse
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
    ):ApiHelper {

    override suspend fun getCurrentWeather(name:String): Response<WeatherResponse> =
        apiService.getCurrentWeather(name)

    override suspend fun getFutureWeatherDetails(
        lat: Double,
        lon: Double
    ): Response<FutureWeatherResponse>  = apiService.getFutureWeatherDetails(lat.toString(), lon.toString())
}