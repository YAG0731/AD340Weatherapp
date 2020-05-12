package com.guoyu.ad340_0

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
            val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String){
        val randomValues = List(7){ Random.nextFloat().rem(100)*100 }
        val forecastItems = randomValues.map{ temp ->
            DailyForecast(temp,getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String{
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anhting below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Way too cold"
            in 32f.rangeTo(65F) -> "Still so cold"
            in 65f.rangeTo(80f) -> "Perfect weather"
            in 80f.rangeTo(100f) -> "This is freaking hot"
            in 100f.rangeTo(Float.MAX_VALUE) -> "Crazy Hot, Help"
            else -> "Does not compute"
        }
    }
}