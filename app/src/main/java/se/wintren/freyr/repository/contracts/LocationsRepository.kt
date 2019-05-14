package se.wintren.freyr.repository.contracts

import io.reactivex.Single
import se.wintren.freyr.repository.database.model.LocationEntity
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

interface LocationsRepository {

    fun getGeoCode(value: String): Single<GeoCodeResponseDTO>

    fun getLocation(id: Long): Single<LocationEntity>

    fun storeLocation(location: LocationEntity): Single<Long>

}