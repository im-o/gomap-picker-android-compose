package id.barakkadev.coremap.data.repository.googlemap

import id.barakkadev.coremap.data.datasource.remote.MapApiService
import id.barakkadev.coremap.data.model.remote.googlemap.MapGeocodeResponse
import id.barakkadev.coremap.data.model.remote.googlemap.request.MapGeocodeRequest
import id.barakkadev.coremap.domain.repository.googlemap.GoogleMapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/** Created by github.com/im-o on 7/16/2023. */

@Singleton
class GoogleMapRepositoryImpl @Inject constructor(
    private val mapApiService: MapApiService,
) : GoogleMapRepository {
    override suspend fun reverseGeocodeApiCall(request: MapGeocodeRequest): Flow<MapGeocodeResponse> {
        return flowOf(
            mapApiService.reverseGeocode(
                request.latlng ?: "",
                request.locationType ?: "",
                request.resultType ?: ""
            )
        )
    }
}