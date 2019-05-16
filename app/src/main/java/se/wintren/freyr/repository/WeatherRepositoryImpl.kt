package se.wintren.freyr.repository

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import se.wintren.freyr.repository.contracts.WeatherRepository
import se.wintren.freyr.repository.network.WeatherAPI
import se.wintren.freyr.repository.network.model.ForecastResponseDTO
import se.wintren.freyr.repository.network.model.WeatherResponseDTO
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Inject

typealias CombinedWeatherDTO = Pair<WeatherResponseDTO, ForecastResponseDTO>

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI,
    private val schedulers: RxSchedulers
) : WeatherRepository {

    override fun getWeather(
        latitude: Float, longitude: Float
    ): Single<CombinedWeatherDTO> {
        val weather: Single<WeatherResponseDTO> = api.getCurrentWeather(latitude, longitude)
        val forecast: Single<ForecastResponseDTO> = api.getWeatherForecast(latitude, longitude)

        val zipperFunction =
            BiFunction<WeatherResponseDTO, ForecastResponseDTO, CombinedWeatherDTO>
            { w, f -> w to f }

        return Single.zip(weather, forecast, zipperFunction)
            .subscribeOn(schedulers.io())
    }

}
