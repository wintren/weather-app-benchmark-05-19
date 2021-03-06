package se.wintren.freyr.repository.contracts

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

interface LocationsRepository {

    fun getGeoCode(value: String): Observable<GeoCodeResponseDTO>

    fun getLocations(): LiveData<List<Location>>

    fun storeLocation(location: Location)

}