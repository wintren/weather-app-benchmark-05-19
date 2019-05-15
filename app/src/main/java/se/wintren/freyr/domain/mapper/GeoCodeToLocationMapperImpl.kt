package se.wintren.freyr.domain.mapper

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.domain.mapper.contract.GeoCodeToLocationMapper

class GeoCodeToLocationMapperImpl : GeoCodeToLocationMapper {

    override fun geoCodeToLocation(geoCode: GeoCode): Location = with(geoCode) {
        Location(
            city = city,
            country = country,
            lat = lat,
            lon = lon
        )
    }

}