package id.barakkadev.coremap.domain.repository.googlemap

import id.barakkadev.coremap.data.model.remote.googlemap.MapGeocodeResponse
import id.barakkadev.coremap.data.model.remote.googlemap.request.MapGeocodeRequest
import kotlinx.coroutines.flow.Flow

/** Created by github.com/im-o on 7/16/2023. */

interface GoogleMapRepository {
    suspend fun reverseGeocodeApiCall(request: MapGeocodeRequest): Flow<MapGeocodeResponse>
}