package se.wintren.freyr.domain.usecase

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.util.RxSchedulers

class GetGeoCodeUseCaseImpl(
    private val repository: LocationsRepository,
    private val mapper: GeoCodeDataMapper,
    private val schedulers: RxSchedulers
) : GetGeoCodeUseCase {

    override fun getGeoCode(value: String): Observable<GeoCode> {
        return repository.getGeoCode(value)
            .observeOn(schedulers.computation())
            .map(mapper::mapResponse)
    }

}