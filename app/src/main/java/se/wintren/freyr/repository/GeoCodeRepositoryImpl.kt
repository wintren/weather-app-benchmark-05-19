package se.wintren.freyr.repository

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

    override fun getLocations(): List<Location> {
        return locationDao.getAllLocations().map { mapper.entityToLocation(it) }
    }

    override fun storeLocation(location: Location) {
        locationDao.insertLocation(mapper.locationToEntity(location))
    }

}
