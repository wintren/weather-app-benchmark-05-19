package se.wintren.freyr.domain.mapper

import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.domain.mapper.contract.LocationEntityMapper
import se.wintren.freyr.repository.database.model.LocationEntity

class LocationEntityMapperImpl : LocationEntityMapper {

    override fun locationToEntity(location: Location): LocationEntity = with(location) {
        LocationEntity(city, country, lon, lat)
    }

    override fun entityToLocation(entity: LocationEntity): Location = with(entity) {
        Location(city, country, lon, lat)
    }

}
