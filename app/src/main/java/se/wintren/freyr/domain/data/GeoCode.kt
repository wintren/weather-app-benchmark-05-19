package se.wintren.freyr.domain.data

data class GeoCode(
    val location: String,
    val region: String,
    val lat: Double,
    val lon: Double
)