package com.example.weatherapp.ui.weatherDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Daily
import com.example.weatherapp.model.FutureWeatherResponse
import com.example.weatherapp.utility.Constants
import kotlinx.android.synthetic.main.daily_weather_item.view.*

class WeatherDetailsRecyclerViewAdapter(val context:Context):
    RecyclerView.Adapter<WeatherDetailsRecyclerViewAdapter.ViewHolder>() {

    private var daily = listOf<Daily>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.daily_weather_item,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.updateUI(daily[position])
    }

    override fun getItemCount(): Int  =  daily.size

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun updateUI(day:Daily) {
            itemView.weekDay.text =  Constants.getDate(day.sunrise.toLong(), "EEEE-MM/dd/YYYY")
            itemView.weatherInfo.text = day.weather.first().description
            itemView.currentTemp.text =String.format(context.resources.getString(R.string.temp),
                day.temp.day.toString())

            itemView.minMaxTemp.text = String.format(context.resources.getString(R.string.minMaxtemp),
                day.temp.max.toString(), day.temp.min.toString())

            itemView.humadity.text = String.format(context.resources.getString(R.string.humadity),
                day.humidity.toString())

            itemView.pressure.text = String.format(context.resources.getString(R.string.pressure),
                day.pressure.toString())


        }
    }

    fun setData(futureWeatherResponse: FutureWeatherResponse) {


         this.daily = futureWeatherResponse.daily



        this.notifyDataSetChanged()
    }
}