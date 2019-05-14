package se.wintren.freyr.domain.usecase.contracts

import se.wintren.freyr.domain.data.Location

interface StoreLocationUseCase {
    fun storeLocation(location: Location)
}