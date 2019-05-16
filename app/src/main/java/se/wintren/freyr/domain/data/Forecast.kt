package se.wintren.freyr.domain.data

data class Forecast(
    val weekday: String,
    val iconUrl: String,
    val temperature: String
)