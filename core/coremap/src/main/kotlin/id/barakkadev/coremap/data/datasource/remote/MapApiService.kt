package id.barakkadev.coremap.data.datasource.remote

import id.barakkadev.coremap.data.model.remote.googlemap.MapGeocodeResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** Created by github.com/im-o on 7/16/2023. */

interface MapApiService {
    @GET("geocode/json")
    suspend fun reverseGeocode(
        @Query("latlng") latlng: String,
        @Query("location_type") locationType: String,
        @Query("result_type") resultType: String,
    ): MapGeocodeResponse
}