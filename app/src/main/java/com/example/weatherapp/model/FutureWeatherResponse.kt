package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("lat") val lat : Double,
    @SerializedName("lon") val lon : Double,
    @SerializedName("timezone") val timezone : String,
    @SerializedName("timezone_offset") val timezone_offset : Int,
    @SerializedName("minutely") val minutely : List<Minutely>,
  //  @SerializedName("hourly") val hourly : List<Hourly>,
    @SerializedName("daily") val daily : List<Daily>
)

data class Minutely (
    @SerializedName("dt") val dt : Int,
    @SerializedName("precipitation") val precipitation : Int
)

data class Hourly (
    @SerializedName("dt") val dt : Int,
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feels_like : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("dew_point") val dew_point : Double,
    @SerializedName("uvi") val uvi : Double,
    @SerializedName("clouds") val clouds : Int,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("wind_speed") val wind_speed : Double,
    @SerializedName("wind_deg") val wind_deg : Int,
    @SerializedName("wind_gust") val wind_gust : Double,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("pop") val pop : Int
)

data class Daily (
    @SerializedName("dt") val dt : Long,
    @SerializedName("sunrise") val sunrise : Long,
    @SerializedName("sunset") val sunset : Long,
    @SerializedName("moonrise") val moonrise : Long,
    @SerializedName("moonset") val moonset : Long,
    @SerializedName("moon_phase") val moon_phase : Double,
    @SerializedName("temp") val temp : Temp,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("dew_point") val dew_point : Double,
    @SerializedName("wind_speed") val wind_speed : Double,
    @SerializedName("wind_deg") val wind_deg : Int,
    @SerializedName("wind_gust") val wind_gust : Double,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("clouds") val clouds : Int,
    @SerializedName("pop") val pop : Double,
    @SerializedName("uvi") val uvi : Double
)

data class Temp (
    @SerializedName("day") val day : Double,
    @SerializedName("min") val min : Double,
    @SerializedName("max") val max : Double,
    @SerializedName("night") val night : Double,
    @SerializedName("eve") val eve : Double,
    @SerializedName("morn") val morn : Double
)

