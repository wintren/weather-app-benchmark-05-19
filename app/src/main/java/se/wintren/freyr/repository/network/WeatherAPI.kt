package se.wintren.freyr.repository.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import se.wintren.freyr.repository.network.model.ForecastResponseDTO
import se.wintren.freyr.repository.network.model.WeatherResponseDTO

interface WeatherAPI {

    @GET("data/2.5/forecast")
    fun getWeatherForecast(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): Single<ForecastResponseDTO>


    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): Single<WeatherResponseDTO>
}
