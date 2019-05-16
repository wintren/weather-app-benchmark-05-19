package se.wintren.freyr.domain.mapper.contract

import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.repository.CombinedWeatherDTO
import se.wintren.freyr.repository.network.model.ForecastResponseDTO

interface WeatherForecastDataMapper {

    fun mapResponse(response: CombinedWeatherDTO): List<Forecast>

}