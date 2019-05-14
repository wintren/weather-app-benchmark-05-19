package se.wintren.freyr.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.repository.database.model.LocationEntity
import se.wintren.freyr.repository.network.GeoCodingAPI
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    val api: GeoCodingAPI
) : LocationsRepository {

    override fun getGeoCode(value: String): Single<GeoCodeResponseDTO> {
        // if in cache, otherwise API
        return api.geocode(value)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                // cache
            }
    }

    override fun getLocation(id: Long): Single<LocationEntity> {
        TODO("not implemented")
    }

    override fun storeLocation(location: LocationEntity): Single<Long> {
        TODO("not implemented")
    }

}
