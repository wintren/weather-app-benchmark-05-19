package se.wintren.freyr.domain.usecase

import io.reactivex.Single
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.domain.mapper.contract.WeatherForecastDataMapper
import se.wintren.freyr.domain.usecase.contracts.GetWeatherForecastUseCase
import se.wintren.freyr.repository.contracts.WeatherRepository
import se.wintren.freyr.util.RxSchedulers

class GetWeatherForecastUseCaseImpl(
    private val repository: WeatherRepository,
    private val mapper: WeatherForecastDataMapper,
    private val schedulers: RxSchedulers
): GetWeatherForecastUseCase {

    override fun getWeather(latitude: Float, longitude: Float): Single<List<Forecast>> {
        return repository.getWeather(latitude, longitude)
            .observeOn(schedulers.computation())
            .map(mapper::mapResponse)
    }
}