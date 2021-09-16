package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utility.CurrentWeatherEvent
import com.example.weatherapp.utility.FutureWeatherEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private  val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weatherResponse = MutableStateFlow<CurrentWeatherEvent>(CurrentWeatherEvent.Empty)
    private val _futureWeatherResponse = MutableStateFlow<FutureWeatherEvent>(FutureWeatherEvent.Empty)

    val weatherResponse : StateFlow<CurrentWeatherEvent>
        get() = _weatherResponse

    val futureWeatherResponse : StateFlow<FutureWeatherEvent>
        get() = _futureWeatherResponse

     fun getCurrentWeather(name:String)  = viewModelScope.launch {
         _weatherResponse.value = CurrentWeatherEvent.Loading
        weatherRepository.getCurrentWeather(name).let { response ->
            if (response.isSuccessful){
                response.let {
                    it.body()?.let {
                        _weatherResponse.value =  CurrentWeatherEvent.Success(it)
                    }
                }
            }else{
                _weatherResponse.value = CurrentWeatherEvent.Error(response.errorBody().toString())
            }
        }
    }


    fun getFutureWeatherDetails(lat:Double, lon:Double)  = viewModelScope.launch {
        weatherRepository.getFutureWeatherDetails(lat, lon).let { response ->
            if (response.isSuccessful){
                response.let {
                    it.body()?.let {
                        _futureWeatherResponse.value =  FutureWeatherEvent.Success(it)
                    }
                }
            }else{
                _futureWeatherResponse.value = FutureWeatherEvent.Error(response.errorBody().toString())
            }
        }
    }
}