package se.wintren.freyr.domain.usecase.contracts

import io.reactivex.Single
import se.wintren.freyr.domain.data.Forecast

interface GetWeatherForecastUseCase {
    fun getWeather(latitude: Float, longitude: Float): Single<List<Forecast>>
}