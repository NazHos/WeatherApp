package com.example.weatherapp.api

import com.example.weatherapp.model.FutureWeatherResponse
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") q: String,
                                  @Query("appid")apiId:String =
                                      "fad7be6d3efab6853151833dfe5b49c2"): Response<WeatherResponse>

    @GET("onecall")
    suspend fun getFutureWeatherDetails(@Query("lat") lat: String,
                                        @Query("lon")lon: String,
                                        @Query("appid")apiId:String =
                                            "fad7be6d3efab6853151833dfe5b49c2"): Response<FutureWeatherResponse>
}