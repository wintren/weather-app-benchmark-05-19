package se.wintren.freyr.domain.usecase.contracts

import io.reactivex.Single
import se.wintren.freyr.domain.data.GeoCode

interface GetGeoCodeUseCase {

    fun getGeoCode(value: String): Single<GeoCode>

}