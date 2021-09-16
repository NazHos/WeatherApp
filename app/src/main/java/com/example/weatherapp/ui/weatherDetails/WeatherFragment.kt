package com.example.weatherapp.ui.weatherDetails

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.FutureWeatherResponse
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.utility.CurrentWeatherEvent
import com.example.weatherapp.utility.FutureWeatherEvent
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_fragment.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var searchItem: MenuItem
    private lateinit var weatherDetailsRecyclerViewAdapter: WeatherDetailsRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weatherDetailsRecyclerViewAdapter = WeatherDetailsRecyclerViewAdapter(requireContext())
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherDetailsRecyclerViewAdapter
        }
        setHasOptionsMenu(true)
        bindViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        searchItem = menu.findItem(R.id.app_bar_search)
        setupSearchView()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupSearchView() {
        val searchView = searchItem.actionView as SearchView
        searchItem.expandActionView()


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()){
                    weatherViewModel.getCurrentWeather(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
    private fun bindViewModel() {

        lifecycleScope.launchWhenStarted {
            weatherViewModel.weatherResponse.collect{ event ->
                when(event){
                    is CurrentWeatherEvent.Success -> {
                        progressBar.visibility = View.INVISIBLE
                        updateUI(event.weatherResponse)
                        weatherViewModel.getFutureWeatherDetails(
                            event.weatherResponse.coord.lat,
                            event.weatherResponse.coord.lon)
                    }
                    is CurrentWeatherEvent.Loading -> {
                       progressBar.visibility = View.VISIBLE
                    }
                    is CurrentWeatherEvent.Error -> {
                        progressBar.visibility = View.GONE
                    Snackbar.make(recyclerView, "Something went wrong",Snackbar.LENGTH_SHORT).show()
                    }
                    is CurrentWeatherEvent.Empty -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            weatherViewModel.futureWeatherResponse.collect{ event ->
                when(event){
                    is FutureWeatherEvent.Success -> {
                        updateRecycleView(event.futureWeatherResponse)
                    }
                    is FutureWeatherEvent.Loading -> {
                    }
                    is FutureWeatherEvent.Error -> {
                    }
                    is FutureWeatherEvent.Empty -> {

                    }
                }

            }
        }

    }

    private fun updateUI(weatherResponse: WeatherResponse) {
        cityName.text = weatherResponse.name
        weatherDetails.text = weatherResponse.weather.first().description
        currentTemp.text = weatherResponse.main.temp.toString()
        minMaxTemp.text = String.format(requireContext().resources.getString(R.string.minMaxtemp),
            weatherResponse.main.temp_min,weatherResponse.main.temp_max)
    }

    private fun updateRecycleView(futureWeatherResponse: FutureWeatherResponse) {
        weatherDetailsRecyclerViewAdapter.setData(futureWeatherResponse)
    }

    companion object {
        fun newInstance() = WeatherFragment()
    }
}