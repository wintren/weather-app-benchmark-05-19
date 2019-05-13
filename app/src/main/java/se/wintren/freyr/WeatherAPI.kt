package se.wintren.freyr

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/forecast")
    fun getWeatherForecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<String>

}
