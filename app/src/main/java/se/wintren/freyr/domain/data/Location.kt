package se.wintren.freyr.domain.data

data class Location(
    var id: Long? = null,
    val cityName: String,
    val region: String,
    val lon: Double,
    val lat: Double
)