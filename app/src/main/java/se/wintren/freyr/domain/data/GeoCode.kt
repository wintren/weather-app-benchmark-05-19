package se.wintren.freyr.domain.data

import se.wintren.freyr.util.EMPTY_STRING

data class GeoCode(
    val city: String = EMPTY_STRING,
    val country: String = EMPTY_STRING,
    val lat: Double = 0.0,
    val lon: Double = 0.0
) {
    fun isEmpty(): Boolean = city == EMPTY_STRING
}