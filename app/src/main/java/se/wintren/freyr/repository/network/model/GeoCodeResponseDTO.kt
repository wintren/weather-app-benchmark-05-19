package se.wintren.freyr.repository.network.model

import com.google.gson.annotations.SerializedName

data class GeoCodeResponseDTO(
    @SerializedName("status") val status: String,
    @SerializedName("results") val results: List<GeoCodeResultDTO>
)

data class GeoCodeResultDTO(
    @SerializedName("address_components") val addressComponents: List<AddressComponentDTO>,
    @SerializedName("formatted_address") val formattedAddress: String,
    @SerializedName("geometry") val geometry: GeometryDTO

    )

data class AddressComponentDTO(
    @SerializedName("long_name") val longName: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("types") val types: List<String>
)

data class GeometryDTO(
    @SerializedName("location") val location: LatLonDTO,
    @SerializedName("location_type") val type: String
)

data class LatLonDTO(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lon: Double
)

