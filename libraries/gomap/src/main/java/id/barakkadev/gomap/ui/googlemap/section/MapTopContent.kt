package id.barakkadev.gomap.ui.googlemap.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import id.barakkadev.coremap.R
import id.barakkadev.coremap.data.model.offline.map.GoMapPickParam

/** Created by github.com/im-o on 7/19/2023. */

@Composable
fun MapTopContent(
    goMapPickParam: GoMapPickParam? = null,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    var isMapLoaded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        GoMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
                onMapLoaded()
            },
            content = {
                content()
            }
        )
        if (!isMapLoaded) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize()
                )
            }
        } else {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (pickMarker, circularProgress) = createRefs()
                Image(
                    painter = painterResource(goMapPickParam?.idResPickIcon ?: R.drawable.red_marker_padding),
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(pickMarker) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )
                if (cameraPositionState.isMoving) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(22.dp)
                            .constrainAs(circularProgress) {
                                start.linkTo(pickMarker.start, margin = 1.dp)
                                end.linkTo(pickMarker.end)
                                top.linkTo(pickMarker.top)
                                bottom.linkTo(pickMarker.bottom, margin = 41.dp)
                            }
                    )
                }
            }

        }
    }
}