package id.barakkadev.lutrago.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import id.barakkadev.coremap.R
import id.barakkadev.coremap.data.model.offline.map.GoMapPickParam
import id.barakkadev.coremap.domain.model.googlemap.MapGeocode
import id.barakkadev.coremap.ui.theme.GoMapPickerTheme
import id.barakkadev.coremap.util.Extensions.myToast
import id.barakkadev.coremap.util.UtilFunctions.logE
import id.barakkadev.gomap.ui.googlemap.GoMapScreen

/** Created by github.com/im-o on 7/12/2023. */

private val singapore = LatLng(-2.5535335, 120.3471928)
private val defaultCameraPosition = CameraPosition.fromLatLngZoom(singapore, 17f)

@Composable
fun SampleMapScreen() {
    val context = LocalContext.current
    var isMapLoaded by remember { mutableStateOf(false) }
    var mapGeocode by remember { mutableStateOf<MapGeocode?>(null) }
    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }

    Box(Modifier.fillMaxSize()) {
        GoMapScreen(
            modifier = Modifier.matchParentSize(),
            goMapPickParam = GoMapPickParam(
                buttonColor = MaterialTheme.colorScheme.primary,
                buttonTextColor = Color.White,
                buttonText = stringResource(id = R.string.confirm_destination),
                isShowBookmark = true,
                idResPickIcon = R.drawable.red_marker_padding,
                idResAddressIcon = R.drawable.red_marker,
                idResBookmarkIcon = R.drawable.bookmark
            ),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            },
            onReverseGeocodeLoaded = {
                mapGeocode = it
                if (isMapLoaded) {
                    context.myToast("isMapLoaded : $mapGeocode")
                    logE("isMapLoaded : $mapGeocode")
                }
            },
            onBookmarkClicked = {
                logE("bookmark clicked : ${mapGeocode?.shortName}")
            },
            content = {}
        )
    }
}

@Preview
@Composable
private fun GoogleMapViewPreview() {
    GoMapPickerTheme {
        SampleMapScreen()
    }
}