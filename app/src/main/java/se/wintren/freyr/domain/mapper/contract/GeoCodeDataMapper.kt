package se.wintren.freyr.domain.mapper.contract

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

interface GeoCodeDataMapper {

    fun mapResponse(response: GeoCodeResponseDTO) : GeoCode

}