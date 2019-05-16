package se.wintren.freyr.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.reactivex.Observable
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.domain.mapper.contract.LocationEntityMapper
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.repository.database.LocationDao
import se.wintren.freyr.repository.network.GeoCodingAPI
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val api: GeoCodingAPI,
    private val locationDao: LocationDao,
    private val mapper: LocationEntityMapper,
    private val schedulers: RxSchedulers
) : LocationsRepository {

    override fun getGeoCode(value: String): Observable<GeoCodeResponseDTO> {
        return api.geocode(value)
            .subscribeOn(schedulers.io())
            .doOnNext {
                // if using cache
            }
    }

    override fun getLocations(): LiveData<List<Location>> {
        val locationEntities = locationDao.getAllLocations()
        return Transformations.map(locationEntities) {
            it.map { entity -> mapper.entityToLocation(entity) }
        }
    }

    override fun storeLocation(location: Location) {
        locationDao.insertLocation(mapper.locationToEntity(location))
    }

}
