package se.wintren.freyr.domain.usecase.contracts

import se.wintren.freyr.domain.data.GeoCode

interface StoreLocationUseCase {
    fun storeLocation(geoCode: GeoCode)
}