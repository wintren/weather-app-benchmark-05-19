package se.wintren.freyr.repository.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import se.wintren.freyr.repository.network.model.GeoCodeResponseDTO

interface GeoCodingAPI {

    @GET("maps/api/geocode/json")
    fun geocode(@Query("address") location: String): Single<GeoCodeResponseDTO>

}
