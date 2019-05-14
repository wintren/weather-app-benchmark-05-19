package se.wintren.freyr.domain.mapper

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

class GeoCodeDataMapperImpl : GeoCodeDataMapper {

    override fun mapResponse(response: GeoCodeResponseDTO): GeoCode = with(response) {
        GeoCode(
            location,
            "Faux",
            2.0,
            2.0
        )
    }

}