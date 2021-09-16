package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base : String,
    @SerializedName("main") val main : Main,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @SerializedName("timezone") val timezone : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int
)

data class Weather (
    @SerializedName("id") val id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)

data class Coord (
    @SerializedName("lon") val lon : Double,
    @SerializedName("lat") val lat : Double
)

data class Clouds (
    @SerializedName("all") val all : Int
)

data class Main (
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feels_like : Double,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int
)

data class Wind (
    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Int,
    @SerializedName("gust") val gust : Double
)