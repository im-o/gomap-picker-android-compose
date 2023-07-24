package id.barakkadev.coremap.domain.usecase.googlemap

import id.barakkadev.coremap.data.model.remote.googlemap.MapGeocodeResponse
import id.barakkadev.coremap.data.model.remote.googlemap.request.MapGeocodeRequest
import id.barakkadev.coremap.domain.repository.googlemap.GoogleMapRepository
import id.barakkadev.coremap.domain.usecase.BaseUseCaseSuspend
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Created by github.com/im-o on 7/16/2023. */

class ReverseGeocodeUseCase @Inject constructor(
    private val repository: GoogleMapRepository
) : BaseUseCaseSuspend<MapGeocodeRequest, Flow<MapGeocodeResponse>>() {
    override suspend fun execute(params: MapGeocodeRequest): Flow<MapGeocodeResponse> {
        return repository.reverseGeocodeApiCall(params)
    }
}