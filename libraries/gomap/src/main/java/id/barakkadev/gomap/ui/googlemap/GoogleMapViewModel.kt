package id.barakkadev.gomap.ui.googlemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.barakkadev.coremap.data.UiState
import id.barakkadev.coremap.data.model.remote.googlemap.request.MapGeocodeRequest
import id.barakkadev.coremap.domain.model.googlemap.MapGeocode
import id.barakkadev.coremap.domain.model.mapper.MapGeocodeMapper
import id.barakkadev.coremap.domain.usecase.googlemap.ReverseGeocodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Created by github.com/im-o on 7/16/2023. */

@HiltViewModel
class GoogleMapViewModel @Inject constructor(
    private val reverseGeocodeUseCase: ReverseGeocodeUseCase
) : ViewModel() {
    private val _uiStateReverseGeocode: MutableStateFlow<UiState<MapGeocode>> = MutableStateFlow(UiState.Loading)
    val uiStateReverseGeocode: StateFlow<UiState<MapGeocode>> = _uiStateReverseGeocode

    fun reverseGeocodeApiCall(latLng: String) {
        val request = MapGeocodeRequest(latLng, "", "")
        viewModelScope.launch {
            try {
                reverseGeocodeUseCase.execute(request)
                    .catch {
                        _uiStateReverseGeocode.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _uiStateReverseGeocode.value = UiState.Success(MapGeocodeMapper.fromRemote(it))
                    }
            } catch (e: Exception) {
                _uiStateReverseGeocode.value = UiState.Error(e.message.toString())
            }
        }
    }
}