package se.wintren.freyr.domain.usecase

import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.mapper.contract.GeoCodeToLocationMapper
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.repository.contracts.LocationsRepository

class StoreLocationUseCaseImpl(
    private val locationsRepository: LocationsRepository,
    private val mapper: GeoCodeToLocationMapper
) : StoreLocationUseCase {

    override fun storeLocation(geoCode: GeoCode) {
        val location = mapper.geoCodeToLocation(geoCode)
        return locationsRepository.storeLocation(location)
    }

}