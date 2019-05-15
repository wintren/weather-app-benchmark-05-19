package se.wintren.freyr.domain.usecase

import androidx.lifecycle.LiveData
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.domain.usecase.contracts.GetLocationsUseCase
import se.wintren.freyr.repository.contracts.LocationsRepository

class GetLocationsUseCaseImpl(
    private val locationsRepository: LocationsRepository
): GetLocationsUseCase {

    override fun getLocations(): LiveData<List<Location>> {
        return locationsRepository.getLocations()
    }
}