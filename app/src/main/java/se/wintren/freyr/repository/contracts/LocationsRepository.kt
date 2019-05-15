package se.wintren.freyr.repository.contracts

import io.reactivex.Observable
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

interface LocationsRepository {

    fun getGeoCode(value: String): Observable<GeoCodeResponseDTO>

    fun getLocations(): List<Location>

    fun storeLocation(location: Location)

}