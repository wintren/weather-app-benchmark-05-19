package se.wintren.freyr.repository.network.model

import com.google.gson.annotations.SerializedName

data class ForecastResponseDTO(
    @SerializedName("list") val list: List<ForecastDTO>
)

data class WeatherResponseDTO(
    @SerializedName("weather") val weather: List<WeatherDTO>,
    @SerializedName("main") val condition: ConditionsDTO,
    @SerializedName("dt") val date: Long
)

data class ForecastDTO(
    @SerializedName("dt") val date: Long,
    @SerializedName("dt_txt") val dateFormatted: String,
    @SerializedName("main") val conditions: ConditionsDTO,
    @SerializedName("weather") val weather: List<WeatherDTO>
)

data class ConditionsDTO(
    @SerializedName("temp") val temp: Float
)

data class WeatherDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String
)