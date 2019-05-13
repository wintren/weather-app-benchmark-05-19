package se.wintren.freyr

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingAPI {

    @GET("maps/api/geocode/json")
    fun geocode(@Query("address") location: String): Single<String>

}
