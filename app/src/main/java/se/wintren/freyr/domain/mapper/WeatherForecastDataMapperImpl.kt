package se.wintren.freyr.domain.mapper

import org.joda.time.DateTime
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.domain.mapper.contract.WeatherForecastDataMapper
import se.wintren.freyr.repository.CombinedWeatherDTO
import se.wintren.freyr.repository.DegreesService

class WeatherForecastDataMapperImpl(
    private val degreesService: DegreesService
) : WeatherForecastDataMapper {

    companion object {
        const val TIME_OF_DAY_FORECAST = 15 // 3pm
        const val WEATHER_IMAGE_URL = "https://openweathermap.org/img/w/"
        const val IMAGE_FORMAT_SUFFIX = ".png"
        const val DEGREES_SYMBOL = "°"
    }

    override fun mapResponse(response: CombinedWeatherDTO): List<Forecast> {

        // Naive implementation for complex data set

        val now = DateTime.now()
        val today = response.first.run {
            Forecast(
                now.dayOfWeek().asText,
                weather.first().icon.fullUrl(),
                condition.temp.convertTemperature()
            )
        }

        val forecasts = mutableListOf(today)
        response
            .second
            .list
            .asSequence()
            .filter { it.dateFormatted.atHour(TIME_OF_DAY_FORECAST) }
            .filter { it.dateFormatted.afterToday() }
            .take(3)
            .forEachIndexed { index, dto ->
                val forecast = Forecast(
                    now.plusDays(index + 1).dayOfWeek().asText,
                    dto.weather.first().icon.fullUrl(),
                    dto.conditions.temp.convertTemperature()
                )
                forecasts.add(forecast)
            }

        return forecasts
    }

    private fun String.atHour(hour: Int): Boolean {
        return split(" ")[1]
            .split(":")[0] == "$hour"
    }

    private fun String.afterToday(): Boolean {
        val dateString = split(" ")[0]
        val date = DateTime.parse(dateString)
        val today = DateTime.now().withTime(TIME_OF_DAY_FORECAST, 1, 0, 0)
        return date.isAfter(today)
    }

    private fun Float.convertTemperature(): String {
        val degrees = degreesService.convertKelvin(this)
        val symbol = degreesService.unitSymbol
        return "$degrees$DEGREES_SYMBOL$symbol"
    }

    private fun String.fullUrl() = "$WEATHER_IMAGE_URL$this$IMAGE_FORMAT_SUFFIX"

}

