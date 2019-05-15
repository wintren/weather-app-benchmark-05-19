package se.wintren.freyr.domain.mapper.contract

import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.repository.database.model.LocationEntity

interface LocationEntityMapper {
    fun locationToEntity(location: Location): LocationEntity
    fun entityToLocation(entity: LocationEntity): Location
}