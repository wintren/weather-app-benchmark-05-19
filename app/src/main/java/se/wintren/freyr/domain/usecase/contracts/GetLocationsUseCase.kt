package se.wintren.freyr.domain.usecase.contracts

import androidx.lifecycle.LiveData
import se.wintren.freyr.domain.data.Location

interface GetLocationsUseCase {
    fun getLocations(): LiveData<List<Location>>
}