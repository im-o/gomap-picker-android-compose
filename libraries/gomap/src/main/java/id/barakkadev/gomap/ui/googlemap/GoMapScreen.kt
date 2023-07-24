package id.barakkadev.gomap.ui.googlemap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import id.barakkadev.coremap.data.model.offline.map.GoMapPickParam
import id.barakkadev.coremap.domain.model.googlemap.MapGeocode
import id.barakkadev.coremap.ui.theme.GoMapPickerTheme
import id.barakkadev.gomap.ui.googlemap.section.GoMapView
import id.barakkadev.gomap.ui.googlemap.section.MapBottomContent
import id.barakkadev.gomap.ui.googlemap.section.MapTopContent

/** Created by github.com/im-o on 7/12/2023. */


@Composable
fun GoMapScreen(
    modifier: Modifier = Modifier,
    goMapPickParam: GoMapPickParam? = null,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    onReverseGeocodeLoaded: (mapGeocode: MapGeocode?) -> Unit = {},
    onBookmarkClicked: (goMapPickParam: GoMapPickParam?) -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    var isMapLoaded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(7.5f),
        ) {
            MapTopContent(
                goMapPickParam = goMapPickParam,
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    isMapLoaded = true
                    onMapLoaded()
                },
                content = content,
            )
        }
        if (isMapLoaded) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(0.dp))
                    .fillMaxSize()
                    .weight(2.0f),
            ) {
                MapBottomContent(
                    goMapPickParam = goMapPickParam,
                    cameraPositionState = cameraPositionState,
                    onReverseGeocodeLoaded = onReverseGeocodeLoaded,
                    onBookmarkClicked = onBookmarkClicked,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoogleMapViewPreview() {
    GoMapPickerTheme {
        GoMapView(Modifier.fillMaxSize())
    }
}