package se.wintren.freyr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.domain.usecase.contracts.GetLocationsUseCase
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    val locations: LiveData<List<Location>> = getLocationsUseCase.getLocations()

}
