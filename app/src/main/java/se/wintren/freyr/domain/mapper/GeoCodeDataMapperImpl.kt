package se.wintren.freyr.domain.mapper

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.repository.network.model.AddressComponentDTO
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO
import se.wintren.freyr.repository.network.model.GeoCodeResultDTO
import se.wintren.freyr.util.EMPTY_STRING

class GeoCodeDataMapperImpl : GeoCodeDataMapper {

    companion object {
        const val ADDRESS_TYPE_LOCALITY = "locality"
        const val ADDRESS_TYPE_COUNTRY = "country"
    }

    override fun mapResponse(response: GeoCodeResponseDTO): GeoCode {
        return response.results.firstOrNull()?.let {
            GeoCode(
                it.getCity(),
                it.getCountry(),
                it.geometry.location.lat,
                it.geometry.location.lon
            )
        } ?: GeoCode()
    }

    private fun GeoCodeResultDTO.getCity(): String = addressComponents.getAddressForType(ADDRESS_TYPE_LOCALITY)
    private fun GeoCodeResultDTO.getCountry(): String = addressComponents.getAddressForType(ADDRESS_TYPE_COUNTRY)

    private fun List<AddressComponentDTO>.getAddressForType(type: String): String {
        var result = EMPTY_STRING
        for (i in 0 until size) {
            val address = this[i]
            if (address.types.contains(type)) {
                result = address.longName
                break
            }
        }
        return result
    }

}