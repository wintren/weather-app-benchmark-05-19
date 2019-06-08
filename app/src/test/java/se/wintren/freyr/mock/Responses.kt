package se.wintren.freyr.mock

import se.wintren.freyr.repository.network.model.*

// Simple created response objects are enough for this app. On a larger scale it would be better
// with a assets-json's handling.

val londonResponse = GeoCodeResponseDTO(
    "OK", listOf(
        GeoCodeResultDTO(
            listOf(
                AddressComponentDTO(
                    "London",
                    "London",
                    listOf("locality", "political")
                ),
                AddressComponentDTO(
                    "United Kingdom",
                    "Gb",
                    listOf("country", "political")
                )
            )
            , "London, UK",
            GeometryDTO(
                LatLonDTO(51.5073509, -0.1277583),
                "APPROXIMATE"
            )
        )
    )
)

val currentWeatherResponse: WeatherResponseDTO = WeatherResponseDTO(
    listOf(WeatherDTO(5, "some1D", "Clouds", "Scattered Clouds")),
    ConditionsDTO(17F),
    1_560_004_033_678L
)

val weatherForecastResponse: ForecastResponseDTO = ForecastResponseDTO(
    listOf(
        ForecastDTO(
            1_560_004_033_678L,
            "2019-06-08 15:00:00",
            ConditionsDTO(16F),
            listOf(WeatherDTO(5, "some1D", "Clouds", "Scattered Clouds"))
        ),
        ForecastDTO(
            1_560_004_033_678L,
            "2019-06-09 15:00:00",
            ConditionsDTO(17F),
            listOf(WeatherDTO(5, "some1D", "Clouds", "Scattered Clouds"))
        ),
        ForecastDTO(
            1_560_004_033_678L,
            "2019-06-10 15:00:00",
            ConditionsDTO(18F),
            listOf(WeatherDTO(5, "some1D", "Clouds", "Scattered Clouds"))
        ),
        ForecastDTO(
            1_560_004_033_678L,
            "2019-06-11 15:00:00",
            ConditionsDTO(17F),
            listOf(WeatherDTO(5, "some1D", "Clouds", "Scattered Clouds"))
        )
    )
)