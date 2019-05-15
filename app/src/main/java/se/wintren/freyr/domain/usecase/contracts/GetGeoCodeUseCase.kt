package se.wintren.freyr.domain.usecase.contracts

import io.reactivex.Observable
import se.wintren.freyr.domain.data.GeoCode

interface GetGeoCodeUseCase {

    fun getGeoCode(value: String): Observable<GeoCode>

}