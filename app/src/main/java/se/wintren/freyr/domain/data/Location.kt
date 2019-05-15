package se.wintren.freyr.domain.data

data class Location(
    val city: String,
    val country: String,
    val lon: Double,
    val lat: Double
) {
    val formatted: String
        get() = "$city, $country"
}