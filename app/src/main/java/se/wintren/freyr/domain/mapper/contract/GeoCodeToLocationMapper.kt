package se.wintren.freyr.domain.mapper.contract

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.data.Location

interface GeoCodeToLocationMapper {
    fun geoCodeToLocation(geoCode: GeoCode): Location
}