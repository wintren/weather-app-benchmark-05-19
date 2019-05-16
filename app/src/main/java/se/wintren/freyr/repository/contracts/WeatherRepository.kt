package se.wintren.freyr.repository.contracts

import io.reactivex.Single
import se.wintren.freyr.repository.CombinedWeatherDTO
import se.wintren.freyr.repository.network.model.ForecastResponseDTO

interface WeatherRepository {

    fun getWeather(latitude: Float, longitude: Float): Single<CombinedWeatherDTO>

}