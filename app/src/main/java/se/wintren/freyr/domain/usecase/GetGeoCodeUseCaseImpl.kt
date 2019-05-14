package se.wintren.freyr.domain.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.repository.contracts.LocationsRepository

class GetGeoCodeUseCaseImpl(
    private val repository: LocationsRepository,
    private val mapper: GeoCodeDataMapper
) : GetGeoCodeUseCase {

    override fun getGeoCode(value: String): Single<GeoCode> {
        return repository.getGeoCode(value)
            .map(mapper::mapResponse)
            .observeOn(Schedulers.computation()) // todo, extract scheduler for testing
    }

}