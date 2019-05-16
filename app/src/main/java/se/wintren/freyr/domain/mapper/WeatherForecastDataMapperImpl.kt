package se.wintren.freyr.domain.mapper

import org.joda.time.DateTime
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.domain.mapper.contract.WeatherForecastDataMapper
import se.wintren.freyr.repository.CombinedWeatherDTO

class WeatherForecastDataMapperImpl(
    // todo: service to determine celsius or fahrenheit
) : WeatherForecastDataMapper {

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
            .filter { it.dateFormatted.atHour("15") }
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

    private fun String.atHour(hour: String): Boolean {
        return split(" ")[1]
            .split(":")[0] == hour
    }

    private fun String.afterToday(): Boolean {
        val dateString = split(" ")[0]
        val date = DateTime.parse(dateString)
        val today = DateTime.now().withTime(15, 1, 0, 0)
        return date.isAfter(today)
    }

    private fun Float.convertTemperature(): String {
        return "${this.toInt() - 200}°c" // work on logic ;)
    }

    private fun String.fullUrl() = "https://openweathermap.org/img/w/$this.png"

}

